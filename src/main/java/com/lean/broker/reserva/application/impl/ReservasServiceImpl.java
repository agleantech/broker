package com.lean.broker.reserva.application.impl;

import com.lean.broker.reserva.application.ReservasService;
import com.lean.broker.reserva.application.dto.ReservaSalidaDto;
import com.lean.broker.reserva.domain.Reserva;
import com.lean.broker.reserva.infrastructure.broker.ReservaProducer;
import com.lean.broker.reserva.infrastructure.controller.dto.ReservaDto;
import com.lean.broker.reserva.infrastructure.repository.ReservasRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservasServiceImpl implements ReservasService {

    private final ReservasRepository repository;
    private final ReservaProducer reservaProducer;

    public ReservasServiceImpl(ReservasRepository repository, ReservaProducer reservaProducer) {
        this.repository = repository;
        this.reservaProducer = reservaProducer;
    }

    @Override
    public ReservaSalidaDto registrarReserva(ReservaDto reserva) {
        Reserva reservaGuardada = new Reserva();
        ReservaSalidaDto reservaSalidaDto = new ReservaSalidaDto();
        try {
            reservaGuardada = repository.save(
                    new Reserva(null,
                            reserva.getFechaIngreso(),
                            reserva.getFechaSalida(),
                            reserva.getTotalDias(),
                            reserva.getNumeroPersonas(),
                            reserva.getTitularReserva(),
                            reserva.getNumeroHabitaciones(),
                            reserva.getNumeroMenores()
                    )
            );
            this.notificarReserva(reservaGuardada.getId(), "Nueva reserva");
            reservaSalidaDto.setRespuesta("OK");
            reservaSalidaDto.setReservaId(reservaGuardada.getId());
            return reservaSalidaDto;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.notificarReserva(-1, "No se pudo crear la reserva. " + reserva.toString());
        }
        reservaSalidaDto.setRespuesta("FALLO");
        reservaSalidaDto.setMensaje("No se pudo crear la reserva");
        return reservaSalidaDto;
    }

    @Override
    public ReservaDto consultarReserva(Integer id) {
        try {
            Reserva reserva = repository.getById(id);
            return new ReservaDto(
                    reserva.getFechaIngreso(),
                    reserva.getFechaSalida(),
                    reserva.getTotalDias(),
                    reserva.getNumeroPersonas(),
                    reserva.getTitularReserva(),
                    reserva.getNumeroHabitaciones(),
                    reserva.getNumeroMenores()
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ReservaDto();
        }
    }

    private void notificarReserva(Integer id, String mensaje) {
        reservaProducer.enviarMensaje(mensaje + " " + id);
    }
}
