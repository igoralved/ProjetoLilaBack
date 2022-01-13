package com.db.jogo.repository;

import com.db.jogo.model.CartaInicio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartaInicioRepository extends CrudRepository<CartaInicio, UUID> {

}