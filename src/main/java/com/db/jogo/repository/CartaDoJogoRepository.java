package com.db.jogo.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.db.jogo.model.CartaDoJogo;

@Repository
public interface CartaDoJogoRepository extends CrudRepository<CartaDoJogo, UUID> {

}