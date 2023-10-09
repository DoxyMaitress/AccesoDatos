import java.io.*;
import java.util.*;
import java.io.StreamTokenizer;
public class Main {
    private static List<Alumno> alumnos = new ArrayList<>();
    private static final String archivo = "alumnos.txt";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            // Menú principal
            System.out.println("Menu Principal:");
            System.out.println("1. Agregar alumno");
            System.out.println("2. Modificar alumno");
            System.out.println("3. Eliminar alumno");
            System.out.println("4. Buscar alumno");
            System.out.println("5. Identificar alumnos para recuperación");
            System.out.println("6. Recuperar último elemento borrado");
            System.out.println("7. Eliminar todos los datos");
            System.out.println("8. Guardar y salir");
            System.out.print("Ingrese una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    agregarAlumno(scanner);
                    break;
                case 2:
                    modificarAlumno(scanner);
                    break;
                case 3:
                    eliminarAlumno(scanner);
                    break;
                case 4:
                    buscarAlumno(scanner);
                    break;
                case 5:
                    identificarRecuperacion();
                    break;
                case 6:
                    recuperarUltimoElementoBorrado();
                    break;
                case 7:
                    eliminarTodosLosDatos();
                    break;
                case 8:
                    guardarDatosEnArchivo();
                    System.exit(0);
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void agregarAlumno(Scanner scanner) {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = scanner.nextLine();
        System.out.print("Asignatura: ");
        String asignatura = scanner.nextLine();
        System.out.print("Nota: ");
        double nota = scanner.nextDouble();
        scanner.nextLine();

        Alumno nuevoAlumno = new Alumno(nombre, apellidos, asignatura, nota);
        alumnos.add(nuevoAlumno);
        System.out.println("Alumno agregado: " + nuevoAlumno);

    }

    private static void modificarAlumno(Scanner scanner) {
        System.out.print("Ingrese el nombre del alumno a modificar: ");
        String nombreBuscado = scanner.nextLine();
        boolean encontrado = false;

        for (Alumno alumno : alumnos) {
            if (alumno.getNombre().equalsIgnoreCase(nombreBuscado)) {
                System.out.print("Nuevo nombre: ");
                String nuevoNombre = scanner.nextLine();
                alumno.setNombre(nuevoNombre);

                System.out.print("Nuevo apellidos: ");
                String nuevosApellidos = scanner.nextLine();
                alumno.setApellidos(nuevosApellidos);

                System.out.print("Nueva asignatura: ");
                String nuevaAsignatura = scanner.nextLine();
                alumno.setAsignatura(nuevaAsignatura);

                System.out.print("Nueva nota: ");
                double nuevaNota = scanner.nextDouble();
                alumno.setNota(nuevaNota);
                scanner.nextLine();  // Consumir la nueva línea

                System.out.println("Alumno modificado: " + alumno);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Alumno no encontrado.");
        }
    }

    private static void eliminarAlumno(Scanner scanner) {
        System.out.print("Ingrese el nombre del alumno a eliminar: ");
        String nombreBuscado = scanner.nextLine();
        boolean encontrado = false;

        for (Alumno alumno : alumnos) {
            if (alumno.getNombre().equalsIgnoreCase(nombreBuscado)) {
                alumnos.remove(alumno);
                System.out.println("Alumno eliminado: " + alumno);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Alumno no encontrado.");
        }
    }

    private static void buscarAlumno(Scanner scanner) {
        System.out.print("Ingrese el nombre o apellidos del alumno a buscar: ");
        String consulta = scanner.nextLine();
        boolean encontrado = false;

        for (Alumno alumno : alumnos) {
            if (alumno.getNombre().equalsIgnoreCase(consulta) || alumno.getApellidos().equalsIgnoreCase(consulta)) {
                System.out.println("Resultado de búsqueda: " + alumno);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Alumno no encontrado.");
        }
    }

    private static void identificarRecuperacion() {
        System.out.print("Ingrese la asignatura para identificar a los alumnos para recuperación: ");

        try {
            StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
            tokenizer.nextToken();
            String asignaturaRecuperacion = tokenizer.sval;

            List<Alumno> alumnosRecuperacion = new ArrayList<>();

            for (Alumno alumno : alumnos) {
                if (alumno.getAsignatura().equalsIgnoreCase(asignaturaRecuperacion) && alumno.getNota() < 5.0) {
                    alumnosRecuperacion.add(alumno);
                }
            }

            if (!alumnosRecuperacion.isEmpty()) {
                System.out.println("Alumnos para recuperación en " + asignaturaRecuperacion + ":");
                for (Alumno alumno : alumnosRecuperacion) {
                    System.out.println(alumno);
                }
            } else {
                System.out.println("No hay alumnos para recuperación en " + asignaturaRecuperacion + ".");
            }
        } catch (IOException e) {
            System.out.println("Error al leer la entrada del usuario.");
        }
    }

    private static void recuperarUltimoElementoBorrado() {
        if (!alumnos.isEmpty()) {
            Alumno ultimoBorrado = alumnos.remove(alumnos.size() - 1);
            System.out.println("Último elemento borrado recuperado: " + ultimoBorrado);
        } else {
            System.out.println("No hay elementos borrados para recuperar.");
        }
    }

    private static void eliminarTodosLosDatos() {
        alumnos.clear();
        System.out.println("Todos los datos han sido eliminados.");
    }

    private static void cargarDatosDesdeArchivo() {
        try (Scanner fileScanner = new Scanner(new File(archivo))) {
            while (fileScanner.hasNextLine()) {
                String linea = fileScanner.nextLine();
                String[] partes = linea.split(",");
                if (partes.length == 4) {
                    String nombre = partes[0];
                    String apellidos = partes[1];
                    String asignatura = partes[2];
                    double nota = Double.parseDouble(partes[3]);
                    Alumno alumno = new Alumno(nombre, apellidos, asignatura, nota);
                    alumnos.add(alumno);
                }
            }
            System.out.println("Datos cargados desde el archivo.");
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe o no se pudo abrir.");
        }
    }

    private static void guardarDatosEnArchivo() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {
            for (Alumno alumno : alumnos) {
                writer.println(alumno.getNombre() + "," + alumno.getApellidos() + ","
                        + alumno.getAsignatura() + "," + alumno.getNota());
            }
            System.out.println("Datos guardados en el archivo.");
        } catch (IOException e) {
            System.out.println("Error al guardar los datos en el archivo.");
        }
    }
}
