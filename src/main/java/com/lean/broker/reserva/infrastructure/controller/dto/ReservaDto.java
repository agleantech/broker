package com.lean.broker.reserva.infrastructure.controller.dto;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class ReservaDto {
    private LocalDate fechaIngreso;
    private LocalDate fechaSalida;
    private Integer totalDias;
    private Integer numeroPersonas;
    private String titularReserva;
    private Integer numeroHabitaciones;
    private Integer numeroMenores;
}
