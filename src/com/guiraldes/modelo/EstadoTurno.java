package com.guiraldes.modelo;

/**
 * Estados posibles de un turno médico:
 * PENDIENTE: el paciente lo solicitó y todavía no fue confirmado.
 * CONFIRMADO: la secretaria lo confirmó.
 * CANCELADO: se anuló y el horario queda libre para otro turno.
 * ATENDIDO: el doctor ya atendió al paciente.
 * Es un enum y no un String para que solo existan estos valores
 * y el compilador detecte cualquier error de escritura.
 */
public enum EstadoTurno {
    PENDIENTE,
    CONFIRMADO,
    CANCELADO,
    ATENDIDO
}