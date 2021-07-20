package com.lean.broker.reserva.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lean.broker.reserva.application.ReservasService;
import com.lean.broker.reserva.application.dto.ReservaSalidaDto;
import com.lean.broker.reserva.infrastructure.controller.dto.ReservaDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ReservasController.class)
class ReservasControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private ReservasService service;
    private ReservaDto reservaDto;
    private ReservaSalidaDto reservaSalidaDto;

    @BeforeEach
    void setUp() {
        reservaDto = new ReservaDto(
                LocalDate.of(2021,07,19),
                LocalDate.of(2021,07,30),
                10,
                5,
                "Bad Name",
                2,
                1
        );
        reservaSalidaDto = new ReservaSalidaDto(
                "OK",
                "",
                0
        );
    }

    @Test
    void registrar() throws Exception {
        when(service.registrarReserva(Mockito.any(ReservaDto.class)))
                .thenReturn(reservaSalidaDto);
        mvc.perform(post("/reserva/registrar-reserva")
                .contentType("application/json")
                .content(mapper.writeValueAsString(reservaDto))
        ).andExpect(jsonPath("$.respuesta").value("OK"));
    }

    @Test
    void consultar() throws Exception {
        when(service.consultarReserva(Mockito.any(Integer.class)))
                .thenReturn(new ReservaDto());
        mvc.perform(get("/reserva/consultar-reserva/1")
        ).andExpect(status().is2xxSuccessful());
    }
}