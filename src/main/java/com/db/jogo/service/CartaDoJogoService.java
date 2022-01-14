package com.db.jogo.service;

import com.db.jogo.model.CartaDoJogo;
import org.springframework.dao.DataAccessException;

import java.util.UUID;

public interface CartaDoJogoService {
    CartaDoJogo findById(UUID id) throws DataAccessException;
    CartaDoJogo saveCartaDoJogo(CartaDoJogo cartaDoJogo) throws DataAccessException;
    Iterable<CartaDoJogo> findAll() throws DataAccessException;
}
