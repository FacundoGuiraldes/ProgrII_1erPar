package com.guiraldes.modelo;

public class Turno {

    private final int id;
    private final Paciente paciente;
    private final Doctor doctor;
    private final String fecha;
    private final String hora;
    private EstadoTurno estado;

    public Turno(int id, Paciente paciente, Doctor doctor, String fecha, String hora) {
        this(id, paciente, doctor, fecha, hora, EstadoTurno.PENDIENTE);
    }

    public Turno(int id, Paciente paciente, Doctor doctor, String fecha, String hora, EstadoTurno estado) {
        this.id = id;
        this.paciente = paciente;
        this.doctor = doctor;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public EstadoTurno getEstado() {
        return estado;
    }

    void setEstado(EstadoTurno estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Turno #" + id + " - Paciente: " + paciente.getNombre() + " " + paciente.getApellido()
                + " - Doctor: " + doctor.getNombre() + " " + doctor.getApellido()
                + " - " + fecha + " " + hora + " - Estado: " + estado;
    }
}