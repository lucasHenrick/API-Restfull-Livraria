package io.github.lucashenrick.livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.lucashenrick.livraria.entity.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

}
