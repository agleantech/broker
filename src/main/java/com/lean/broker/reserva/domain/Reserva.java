package com.lean.broker.reserva.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(
        name = "tbl_reserva",
        uniqueConstraints = {
                @UniqueConstraint(name = "reserva_constraint", columnNames = "id")
        }
)
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Reserva implements Serializable {
    @Id
    @SequenceGenerator(name = "sq_reserva", sequenceName = "sq_reserva", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_reserva")
    private Integer id;
    private LocalDate fechaIngreso;
    private LocalDate fechaSalida;
    private Integer totalDias;
    private Integer numeroPersonas;
    private String titularReserva;
    private Integer numeroHabitaciones;
    private Integer numeroMenores;
}
