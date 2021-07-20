package com.lean.broker.config;

import com.lean.broker.reserva.domain.Reserva;
import com.lean.broker.reserva.infrastructure.repository.ReservasRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
public class DatosPruebaConfig {

    @Bean
    CommandLineRunner cargarReservas(ReservasRepository repository) {
        return args -> repository.saveAll(
                Arrays.asList(
                        new Reserva(null,
                                LocalDate.now(),
                                LocalDate.of(2021,7,30),
                                10,
                                5,
                                "John Doe",
                                2,
                                0),
                        new Reserva(null,
                                LocalDate.now(),
                                LocalDate.of(2021,8,30),
                                30,
                                5,
                                "Max Powell",
                                2,
                                0)
                )
        );
    }
}
