package com.guiraldes.modelo;

public class Secretaria extends Usuario {
    private String legajo;
    private String turno;

    public Secretaria() {
        super();
    }

    public Secretaria(String nombre, String apellido, String dni, String email, String password,
                       String legajo, String turno) {
        super(nombre, apellido, dni, email, password);
        this.legajo = legajo;
        this.turno = turno;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    @Override
    public String mostrarDatos() {
        return super.mostrarDatos() + " | Legajo: " + legajo + " | Turno laboral: " + turno;
    }

    public String asignarTurno(String paciente, String doctor, String fecha) {
        return "Turno asignado: " + paciente + " con " + doctor + " el " + fecha;
    }
}