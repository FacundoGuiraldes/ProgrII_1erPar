package com.guiraldes.modelo;

public abstract class Usuario {
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private String password;

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String dni, String email, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String mostrarDatos() {
        return "Nombre: " + nombre + " " + apellido + " | DNI: " + dni + " | Email: " + email;
    }

    public boolean validarPassword(String passwordIngresada) {
        if (this.password == null) {
            return false;
        }
        return this.password.equals(passwordIngresada);
    }
}
