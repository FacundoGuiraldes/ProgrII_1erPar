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

    public Turno asignarTurno(Agenda agenda, Paciente paciente, Doctor doctor, String fecha, String hora) {
        return agenda.crearTurno(paciente, doctor, fecha, hora);
    }

    public void confirmarTurno(Agenda agenda, int idTurno) {
        agenda.confirmarTurno(idTurno);
    }

    public void cancelarTurno(Agenda agenda, int idTurno) {
        agenda.cancelarTurno(idTurno);
    }
}