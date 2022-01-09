package com.db.jogo.repository;

import com.db.jogo.model.CartaInicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartaInicioRepository extends JpaRepository<CartaInicio, UUID> {
}
