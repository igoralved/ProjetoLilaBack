package com.db.jogo.service;

import com.db.jogo.model.CartaDoJogo;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartaDoJogoService {

	Optional<CartaDoJogo> findById(UUID id) throws DataAccessException;

	CartaDoJogo saveCartaDoJogo(CartaDoJogo cartaInicio) throws DataAccessException;

	List<CartaDoJogo> findAll() throws DataAccessException;
}