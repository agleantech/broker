package com.lean.broker.reserva.infrastructure.controller;

import com.lean.broker.reserva.application.ReservasService;
import com.lean.broker.reserva.application.dto.ReservaSalidaDto;
import com.lean.broker.reserva.infrastructure.controller.dto.ReservaDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("reserva")
public class ReservasController {

    private final ReservasService service;

    public ReservasController(ReservasService service) {
        this.service = service;
    }

    @PostMapping(value = "/registrar-reserva", produces = MediaType.APPLICATION_JSON_VALUE)
    public ReservaSalidaDto registrar(@RequestBody ReservaDto reserva) {
        return service.registrarReserva(reserva);
    }

    @GetMapping("/consultar-reserva/{id}")
    public ReservaDto consultar(@PathVariable("id") Integer id) {
        return service.consultarReserva(id);
    }
}
