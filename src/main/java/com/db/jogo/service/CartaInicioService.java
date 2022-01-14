package com.db.jogo.service;

import java.util.UUID;

import org.springframework.dao.DataAccessException;

import com.db.jogo.model.CartaInicio;
import com.db.jogo.model.CartaObjetivo;

public interface CartaInicioService {

    CartaInicio saveCartaInicio(CartaInicio cartaInicio) throws DataAccessException;
    Iterable<CartaInicio> findAll() throws DataAccessException;
    CartaInicio findById(UUID id) throws DataAccessException;


}
