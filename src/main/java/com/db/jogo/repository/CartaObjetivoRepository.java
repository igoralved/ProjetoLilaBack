package com.db.jogo.repository;

import com.db.jogo.model.CartaObjetivo;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CartaObjetivoRepository extends CrudRepository<CartaObjetivo, UUID> {
}
