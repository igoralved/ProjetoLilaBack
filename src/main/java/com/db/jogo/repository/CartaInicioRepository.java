package com.db.jogo.repository;

import com.db.jogo.model.CartaInicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartaInicioRepository extends JpaRepository<CartaInicio, UUID> {
}
