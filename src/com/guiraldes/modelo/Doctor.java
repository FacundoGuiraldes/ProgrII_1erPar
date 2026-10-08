package com.guiraldes.modelo;

public class Doctor extends Usuario implements Notificable {
    private String matricula;
    private String especialidad;

    public Doctor() {
        super();
    }

    public Doctor(String nombre, String apellido, String dni, String email, String password,
                  String matricula, String especialidad) {
        super(nombre, apellido, dni, email, password);
        this.matricula = matricula;
        this.especialidad = especialidad;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String mostrarDatos() {
        return super.mostrarDatos() + " | Matrícula: " + matricula + " | Especialidad: " + especialidad;
    }

    public String atenderTurno(String paciente, String fecha) {
        return "Dr/a. " + getApellido() + " atendió a " + paciente + " el " + fecha;
    }

    @Override
    public void enviarNotificacion(String mensaje) {
        System.out.println("Notificación para Dr/a. " + getApellido() + ": " + mensaje);
    }
}