package com.guiraldes.modelo;

public class Secretaria extends Usuario {
    private String legajo;
    private String turnoLaboral;

    public Secretaria() {
        super();
    }

    public Secretaria(String nombre, String apellido, String dni, String email, String password,
                       String legajo, String turnoLaboral) {
        super(nombre, apellido, dni, email, password);
        this.legajo = legajo;
        this.turnoLaboral = turnoLaboral;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public String getTurnoLaboral() {
        return turnoLaboral;
    }

    public void setTurnoLaboral(String turnoLaboral) {
        this.turnoLaboral = turnoLaboral;
    }

    @Override
    public String mostrarDatos() {
        return super.mostrarDatos() + " | Legajo: " + legajo + " | Turno laboral: " + turnoLaboral;
    }

    public String asignarTurno(String paciente, String doctor, String fecha) {
        return "Turno asignado: " + paciente + " con " + doctor + " el " + fecha;
    }
}