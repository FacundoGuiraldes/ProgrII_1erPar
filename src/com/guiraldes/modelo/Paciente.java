package com.guiraldes.modelo;

public class Paciente extends Usuario implements Notificable {
    private String obraSocial;
    private String numeroAfiliado;

    public Paciente() {
        super();
    }

    public Paciente(String nombre, String apellido, String dni, String email, String password,
                     String obraSocial, String numeroAfiliado) {
        super(nombre, apellido, dni, email, password);
        this.obraSocial = obraSocial;
        this.numeroAfiliado = numeroAfiliado;
    }

    public String getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(String obraSocial) {
        this.obraSocial = obraSocial;
    }

    public String getNumeroAfiliado() {
        return numeroAfiliado;
    }

    public void setNumeroAfiliado(String numeroAfiliado) {
        this.numeroAfiliado = numeroAfiliado;
    }

    @Override
    public String mostrarDatos() {
        return super.mostrarDatos() + " | Obra Social: " + obraSocial + " | N° Afiliado: " + numeroAfiliado;
    }

    public Turno solicitarTurno(Agenda agenda, Doctor doctor, String fecha, String hora) {
        return agenda.crearTurno(this, doctor, fecha, hora);
    }

    @Override
    public void enviarNotificacion(String mensaje) {
        System.out.println("Notificación para paciente " + getNombre() + ": " + mensaje);
    }
}