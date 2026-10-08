package com.guiraldes.modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaTexto {

    public static void guardarPacientes(List<Paciente> pacientes, String archivo) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));
        for (Paciente p : pacientes) {
            writer.write(p.getNombre() + ";" + p.getApellido() + ";" + p.getDni() + ";"
                    + p.getEmail() + ";" + p.getPassword() + ";"
                    + p.getObraSocial() + ";" + p.getNumeroAfiliado());
            writer.newLine();
        }
        writer.close();
    }

    public static List<Paciente> cargarPacientes(String archivo) throws IOException {
        List<Paciente> pacientes = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(archivo));
        String linea;
        while ((linea = reader.readLine()) != null) {
            String[] datos = linea.split(";");
            Paciente p = new Paciente(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], datos[6]);
            pacientes.add(p);
        }
        reader.close();
        return pacientes;
    }

    public static void guardarDoctores(List<Doctor> doctores, String archivo) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));
        for (Doctor d : doctores) {
            writer.write(d.getNombre() + ";" + d.getApellido() + ";" + d.getDni() + ";"
                    + d.getEmail() + ";" + d.getPassword() + ";"
                    + d.getMatricula() + ";" + d.getEspecialidad());
            writer.newLine();
        }
        writer.close();
    }

    public static List<Doctor> cargarDoctores(String archivo) throws IOException {
        List<Doctor> doctores = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(archivo));
        String linea;
        while ((linea = reader.readLine()) != null) {
            String[] datos = linea.split(";");
            Doctor d = new Doctor(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], datos[6]);
            doctores.add(d);
        }
        reader.close();
        return doctores;
    }
}