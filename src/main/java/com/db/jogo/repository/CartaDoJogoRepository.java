package com.db.jogo.repository;

import com.db.jogo.model.CartaDoJogo;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CartaDoJogoRepository extends CrudRepository<CartaDoJogo, UUID> {
}
