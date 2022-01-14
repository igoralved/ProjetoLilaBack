package com.db.jogo.repository;

import com.db.jogo.model.CartaDoJogo;
import com.db.jogo.model.CartaInicio;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CartaInicioRepository extends CrudRepository<CartaInicio, UUID> {
}
