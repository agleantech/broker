package com.lean.broker.reserva.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ReservaSalidaDto {

    private String respuesta;
    private String mensaje;
    private Integer reservaId;
}
