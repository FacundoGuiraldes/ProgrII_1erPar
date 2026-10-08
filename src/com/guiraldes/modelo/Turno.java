package com.guiraldes.modelo;

public class Turno {

    private final int id;
    private Paciente paciente;
    private Doctor doctor;
    private String fecha;
    private String hora;
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

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public EstadoTurno getEstado() {
        return estado;
    }

    public void setEstado(EstadoTurno estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Turno #" + id + " - Paciente: " + paciente.getNombre() + " " + paciente.getApellido()
                + " - Doctor: " + doctor.getNombre() + " " + doctor.getApellido()
                + " - " + fecha + " " + hora + " - Estado: " + estado;
    }
}