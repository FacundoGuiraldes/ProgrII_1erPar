package com.guiraldes.main;

import com.guiraldes.modelo.Paciente;
import com.guiraldes.modelo.Doctor;
import com.guiraldes.modelo.Secretaria;
import com.guiraldes.modelo.Turno;
import com.guiraldes.modelo.Agenda;
import com.guiraldes.modelo.PersistenciaTexto;
import com.guiraldes.modelo.PersistenciaXML;

import java.util.Arrays;
import java.util.List;

public class Principal {
    public static void main(String[] args) {

        Paciente paciente = new Paciente("Juan", "Pérez", "30111222", "juan@mail.com",
                "1234", "OSDE", "A0001");

        Doctor doctor = new Doctor("Laura", "Gómez", "28555666", "laura@mail.com",
                "abcd", "MP1234", "Cardiología");

        Secretaria secretaria = new Secretaria();
        secretaria.setNombre("Ana");
        secretaria.setApellido("López");
        secretaria.setDni("32999888");
        secretaria.setEmail("ana@mail.com");
        secretaria.setPassword("xyz");
        secretaria.setLegajo("SEC001");
        secretaria.setTurno("Mañana");

        System.out.println(paciente.mostrarDatos());
        System.out.println(doctor.mostrarDatos());
        System.out.println(secretaria.mostrarDatos());

        System.out.println(paciente.solicitarTurno("Cardiología", "10/09/2026"));
        System.out.println(doctor.atenderTurno("Juan Pérez", "10/09/2026"));
        System.out.println(secretaria.asignarTurno("Juan Pérez", "Dra. Gómez", "10/09/2026"));

        paciente.enviarNotificacion("Su turno fue confirmado.");
        doctor.enviarNotificacion("Tiene un nuevo turno asignado.");

        Agenda agenda = new Agenda();
        agenda.agregarTurno(new Turno(1, paciente, doctor, "10/09/2026", "09:00"));
        agenda.agregarTurno(new Turno(2, paciente, doctor, "11/09/2026", "10:30"));
        agenda.agregarTurno(new Turno(3, paciente, doctor, "12/09/2026", "14:00"));

        System.out.println("--- Búsqueda recursiva ---");
        System.out.println(agenda.buscarTurnoPorIdRecursivo(2));
        System.out.println(agenda.buscarTurnoPorIdRecursivo(99));

        System.out.println("--- Búsqueda iterativa ---");
        System.out.println(agenda.buscarTurnoPorIdIterativo(2));
        System.out.println(agenda.buscarTurnoPorIdIterativo(99));

        try {
            System.out.println("--- Persistencia en texto ---");
            PersistenciaTexto.guardarPacientes(Arrays.asList(paciente), "pacientes.txt");
            PersistenciaTexto.guardarDoctores(Arrays.asList(doctor), "doctores.txt");

            List<Paciente> pacientesCargados = PersistenciaTexto.cargarPacientes("pacientes.txt");
            List<Doctor> doctoresCargados = PersistenciaTexto.cargarDoctores("doctores.txt");

            for (Paciente p : pacientesCargados) {
                System.out.println(p.mostrarDatos());
            }
            for (Doctor d : doctoresCargados) {
                System.out.println(d.mostrarDatos());
            }
        } catch (Exception e) {
            System.out.println("Error en persistencia de texto: " + e.getMessage());
        }

        try {
            System.out.println("--- Persistencia en XML ---");
            PersistenciaXML.guardarTurnos(agenda.getTurnos(), "turnos.xml");

            List<Turno> turnosCargados = PersistenciaXML.cargarTurnos("turnos.xml");
            for (Turno t : turnosCargados) {
                System.out.println(t);
            }
        } catch (Exception e) {
            System.out.println("Error en persistencia de XML: " + e.getMessage());
        }
    }
}