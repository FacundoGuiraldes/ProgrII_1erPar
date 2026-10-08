package com.guiraldes.main;

import com.guiraldes.modelo.Paciente;
import com.guiraldes.modelo.Doctor;
import com.guiraldes.modelo.Secretaria;
import com.guiraldes.modelo.Turno;
import com.guiraldes.modelo.Agenda;
import com.guiraldes.modelo.EstadoTurno;
import com.guiraldes.persistencia.PersistenciaTexto;
import com.guiraldes.persistencia.PersistenciaXML;

import java.util.Arrays;
import java.util.List;

public class Principal {

    private static Paciente paciente;
    private static Doctor doctor;
    private static Secretaria secretaria;

    public static void main(String[] args) {
        mostrarUsuarios();

        Agenda agenda = new Agenda();
        demostrarFlujoDeTurnos(agenda);
        demostrarBusquedas(agenda);
        demostrarValidaciones(agenda);
        demostrarPersistencia(agenda);
    }

    private static void mostrarUsuarios() {
        paciente = new Paciente("Juan", "Pérez", "30111222", "juan@mail.com",
                "1234", "OSDE", "A0001");

        doctor = new Doctor("Laura", "Gómez", "28555666", "laura@mail.com",
                "abcd", "MP1234", "Cardiología");

        secretaria = new Secretaria();
        secretaria.setNombre("Ana");
        secretaria.setApellido("López");
        secretaria.setDni("32999888");
        secretaria.setEmail("ana@mail.com");
        secretaria.setPassword("xyz");
        secretaria.setLegajo("SEC001");
        secretaria.setTurnoLaboral("Mañana");

        System.out.println(paciente.mostrarDatos());
        System.out.println(doctor.mostrarDatos());
        System.out.println(secretaria.mostrarDatos());
    }

    private static void demostrarFlujoDeTurnos(Agenda agenda) {
        System.out.println(paciente.solicitarTurno(agenda, doctor, "10/09/2026", "09:00"));
        System.out.println(secretaria.asignarTurno(agenda, paciente, doctor, "11/09/2026", "10:30"));
        agenda.agregarTurno(new Turno(3, paciente, doctor, "12/09/2026", "14:00"));

        secretaria.confirmarTurno(agenda, 1);
        System.out.println(agenda.buscarTurnoPorIdIterativo(1));
        doctor.atenderTurno(agenda, 1);
        System.out.println(agenda.buscarTurnoPorIdIterativo(1));

        paciente.enviarNotificacion("Su turno fue confirmado.");
        doctor.enviarNotificacion("Tiene un nuevo turno asignado.");
    }

    private static void demostrarBusquedas(Agenda agenda) {
        System.out.println("--- Búsqueda recursiva ---");
        System.out.println(agenda.buscarTurnoPorIdRecursivo(2));
        System.out.println(agenda.buscarTurnoPorIdRecursivo(99));

        System.out.println("--- Búsqueda iterativa ---");
        System.out.println(agenda.buscarTurnoPorIdIterativo(2));
        System.out.println(agenda.buscarTurnoPorIdIterativo(99));

        System.out.println("--- Turnos por estado ---");
        for (EstadoTurno estado : EstadoTurno.values()) {
            System.out.println(estado + ": " + agenda.contarTurnosPorEstado(estado));
        }
    }

    private static void demostrarValidaciones(Agenda agenda) {
        System.out.println("--- Validaciones de la agenda ---");

        try {
            agenda.crearTurno(paciente, doctor, "11/09/2026", "10:30");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error esperado: " + e.getMessage());
        }

        try {
            agenda.agregarTurno(new Turno(3, paciente, doctor, "20/09/2026", "08:00"));
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error esperado: " + e.getMessage());
        }

        try {
            doctor.atenderTurno(agenda, 2);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error esperado: " + e.getMessage());
        }

        Doctor otroDoctor = new Doctor("Pedro", "Ruiz", "27444555", "pedro@mail.com",
                "efgh", "MP5678", "Clínica Médica");
        try {
            otroDoctor.atenderTurno(agenda, 1);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error esperado: " + e.getMessage());
        }

        secretaria.cancelarTurno(agenda, 2);
        System.out.println(agenda.buscarTurnoPorIdIterativo(2));
        Turno turnoNuevo = secretaria.asignarTurno(agenda, paciente, doctor, "11/09/2026", "10:30");
        System.out.println(turnoNuevo);
    }

    private static void demostrarPersistencia(Agenda agenda) {
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