package com.lean.broker.reserva.infrastructure.repository;

import com.lean.broker.reserva.domain.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservasRepository extends JpaRepository<Reserva, Integer> {}
