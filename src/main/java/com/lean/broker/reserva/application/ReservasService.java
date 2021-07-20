package com.lean.broker.reserva.application;

import com.lean.broker.reserva.application.dto.ReservaSalidaDto;
import com.lean.broker.reserva.infrastructure.controller.dto.ReservaDto;

public interface ReservasService {

    ReservaSalidaDto registrarReserva(ReservaDto reserva);

    ReservaDto consultarReserva(Integer id);

}
