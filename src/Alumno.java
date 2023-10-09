import java.io.*;
import java.util.*;

class Alumno {
    private String nombre;
    private String apellidos;
    private String asignatura;
    private double nota;

    public Alumno(String nombre, String apellidos, String asignatura, double nota) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.asignatura = asignatura;
        this.nota = nota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }


    @Override
    public String toString() {
        return nombre + " " + apellidos + " - " + asignatura + ": " + nota;
    }
}