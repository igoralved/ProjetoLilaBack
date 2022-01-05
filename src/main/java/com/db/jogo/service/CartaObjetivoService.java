package com.db.jogo.service;

import com.db.jogo.model.CartaObjetivo;
import org.springframework.dao.DataAccessException;

public interface CartaObjetivoService {
    CartaObjetivo saveCartaObjetivo(CartaObjetivo cartaObjetivo) throws DataAccessException;
    Iterable<CartaObjetivo> findAll() throws DataAccessException;
    CartaObjetivo findById(Integer id) throws DataAccessException;
}
