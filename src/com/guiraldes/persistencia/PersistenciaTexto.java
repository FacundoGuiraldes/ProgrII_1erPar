package com.guiraldes.persistencia;

import com.guiraldes.modelo.Doctor;
import com.guiraldes.modelo.Paciente;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaTexto {

    public static void guardarPacientes(List<Paciente> pacientes, String archivo) throws IOException {
        List<String> lineas = new ArrayList<>();
        for (Paciente p : pacientes) {
            lineas.add(String.join(";", p.getNombre(), p.getApellido(), p.getDni(), p.getEmail(),
                    p.getPassword(), p.getObraSocial(), p.getNumeroAfiliado()));
        }
        escribirLineas(lineas, archivo);
    }

    public static List<Paciente> cargarPacientes(String archivo) throws IOException {
        List<Paciente> pacientes = new ArrayList<>();
        for (String[] datos : leerCampos(archivo, 7)) {
            pacientes.add(new Paciente(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], datos[6]));
        }
        return pacientes;
    }

    public static void guardarDoctores(List<Doctor> doctores, String archivo) throws IOException {
        List<String> lineas = new ArrayList<>();
        for (Doctor d : doctores) {
            lineas.add(String.join(";", d.getNombre(), d.getApellido(), d.getDni(), d.getEmail(),
                    d.getPassword(), d.getMatricula(), d.getEspecialidad()));
        }
        escribirLineas(lineas, archivo);
    }

    public static List<Doctor> cargarDoctores(String archivo) throws IOException {
        List<Doctor> doctores = new ArrayList<>();
        for (String[] datos : leerCampos(archivo, 7)) {
            doctores.add(new Doctor(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], datos[6]));
        }
        return doctores;
    }

    private static void escribirLineas(List<String> lineas, String archivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, StandardCharsets.UTF_8))) {
            for (String linea : lineas) {
                writer.write(linea);
                writer.newLine();
            }
        }
    }

    private static List<String[]> leerCampos(String archivo, int cantidadCampos) throws IOException {
        List<String[]> registros = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo, StandardCharsets.UTF_8))) {
            String linea;
            int numeroLinea = 0;
            while ((linea = reader.readLine()) != null) {
                numeroLinea++;
                if (linea.isBlank()) {
                    continue;
                }
                String[] campos = linea.split(";", -1);
                if (campos.length != cantidadCampos) {
                    throw new IOException("Línea " + numeroLinea + " de " + archivo + ": se esperaban "
                            + cantidadCampos + " campos y hay " + campos.length + ".");
                }
                registros.add(campos);
            }
        }
        return registros;
    }
}