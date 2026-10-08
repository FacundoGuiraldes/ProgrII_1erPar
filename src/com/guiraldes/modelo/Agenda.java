package com.guiraldes.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Agenda {

    private List<Turno> turnos;
    private int proximoId;

    public Agenda() {
        this.turnos = new ArrayList<>();
        this.proximoId = 1;
    }

    public void agregarTurno(Turno turno) {
        if (turno == null) {
            throw new IllegalArgumentException("El turno no puede ser nulo.");
        }
        if (turno.getPaciente() == null) {
            throw new IllegalArgumentException("El turno debe tener un paciente.");
        }
        if (turno.getDoctor() == null) {
            throw new IllegalArgumentException("El turno debe tener un doctor.");
        }
        if (buscarTurnoPorIdIterativo(turno.getId()) != null) {
            throw new IllegalArgumentException("Ya existe un turno con el id " + turno.getId() + ".");
        }
        if (hayConflictoDeHorario(turno)) {
            throw new IllegalArgumentException("El doctor ya tiene un turno el " + turno.getFecha()
                    + " a las " + turno.getHora() + ".");
        }
        int posicion = 0;
        while (posicion < turnos.size() && turnos.get(posicion).getId() < turno.getId()) {
            posicion++;
        }
        turnos.add(posicion, turno);
        if (turno.getId() >= proximoId) {
            proximoId = turno.getId() + 1;
        }
    }

    public Turno crearTurno(Paciente paciente, Doctor doctor, String fecha, String hora) {
        Turno turno = new Turno(proximoId, paciente, doctor, fecha, hora);
        agregarTurno(turno);
        return turno;
    }

    public void confirmarTurno(int id) {
        Turno turno = buscarOFallar(id);
        if (turno.getEstado() != EstadoTurno.PENDIENTE) {
            throw new IllegalStateException("Solo se puede confirmar un turno PENDIENTE. Estado actual: "
                    + turno.getEstado() + ".");
        }
        turno.setEstado(EstadoTurno.CONFIRMADO);
    }

    public void cancelarTurno(int id) {
        Turno turno = buscarOFallar(id);
        if (turno.getEstado() != EstadoTurno.PENDIENTE && turno.getEstado() != EstadoTurno.CONFIRMADO) {
            throw new IllegalStateException("Solo se puede cancelar un turno PENDIENTE o CONFIRMADO. Estado actual: "
                    + turno.getEstado() + ".");
        }
        turno.setEstado(EstadoTurno.CANCELADO);
    }

    public void marcarComoAtendido(int id) {
        Turno turno = buscarOFallar(id);
        if (turno.getEstado() != EstadoTurno.CONFIRMADO) {
            throw new IllegalStateException("Solo se puede atender un turno CONFIRMADO. Estado actual: "
                    + turno.getEstado() + ".");
        }
        turno.setEstado(EstadoTurno.ATENDIDO);
    }

    public List<Turno> getTurnos() {
        return Collections.unmodifiableList(turnos);
    }

    public int contarTurnosPorEstado(EstadoTurno estado) {
        int cantidad = 0;
        for (Turno turno : turnos) {
            if (turno.getEstado() == estado) {
                cantidad++;
            }
        }
        return cantidad;
    }

    private Turno buscarOFallar(int id) {
        Turno turno = buscarTurnoPorIdIterativo(id);
        if (turno == null) {
            throw new IllegalArgumentException("No existe un turno con el id " + id + ".");
        }
        return turno;
    }

    private boolean hayConflictoDeHorario(Turno nuevo) {
        for (Turno existente : turnos) {
            if (existente.getEstado() != EstadoTurno.CANCELADO
                    && sonIguales(existente.getDoctor().getMatricula(), nuevo.getDoctor().getMatricula())
                    && sonIguales(existente.getFecha(), nuevo.getFecha())
                    && sonIguales(existente.getHora(), nuevo.getHora())) {
                return true;
            }
        }
        return false;
    }

    private boolean sonIguales(String a, String b) {
        return a != null && a.equals(b);
    }

    public Turno buscarTurnoPorIdRecursivo(int id) {
        return buscarTurnoPorIdRecursivo(id, 0, turnos.size() - 1);
    }

    private Turno buscarTurnoPorIdRecursivo(int id, int inicio, int fin) {
        if (inicio > fin) {
            return null;
        }
        int medio = inicio + (fin - inicio) / 2;
        Turno turnoMedio = turnos.get(medio);
        if (turnoMedio.getId() == id) {
            return turnoMedio;
        }
        if (id < turnoMedio.getId()) {
            return buscarTurnoPorIdRecursivo(id, inicio, medio - 1);
        }
        return buscarTurnoPorIdRecursivo(id, medio + 1, fin);
    }

    public Turno buscarTurnoPorIdIterativo(int id) {
        for (Turno turno : turnos) {
            if (turno.getId() == id) {
                return turno;
            }
        }
        return null;
    }
}