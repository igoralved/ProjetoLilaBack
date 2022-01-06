package com.db.jogo.service;

import com.db.jogo.model.CartaObjetivo;
import org.springframework.dao.DataAccessException;

import java.util.UUID;

public interface CartaObjetivoService {
    CartaObjetivo saveCartaObjetivo(CartaObjetivo cartaObjetivo) throws DataAccessException;
    Iterable<CartaObjetivo> findAll() throws DataAccessException;
    CartaObjetivo findById(UUID id) throws DataAccessException;
}
