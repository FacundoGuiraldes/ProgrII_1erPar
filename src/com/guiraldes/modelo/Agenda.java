package com.guiraldes.modelo;

import java.util.ArrayList;
import java.util.List;

public class Agenda {

    private List<Turno> turnos;

    public Agenda() {
        this.turnos = new ArrayList<>();
    }

    public void agregarTurno(Turno turno) {
        turnos.add(turno);
    }

    public List<Turno> getTurnos() {
        return turnos;
    }

    public Turno buscarTurnoPorIdRecursivo(int id) {
        return buscarTurnoPorIdRecursivo(this.turnos, id, 0);
    }

    private Turno buscarTurnoPorIdRecursivo(List<Turno> turnos, int id, int indice) {
        if (indice >= turnos.size()) {
            return null;
        }
        if (turnos.get(indice).getId() == id) {
            return turnos.get(indice);
        }
        return buscarTurnoPorIdRecursivo(turnos, id, indice + 1);
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