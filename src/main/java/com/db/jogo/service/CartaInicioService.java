package com.db.jogo.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.DataAccessException;

import com.db.jogo.model.CartaInicio;

public interface CartaInicioService {

    Optional<CartaInicio> findById(UUID id) throws DataAccessException;
    CartaInicio saveCartaInicio(CartaInicio cartaInicio) throws DataAccessException;
    ArrayList<CartaInicio> findAllCartaInicio() throws DataAccessException;
    Iterable<CartaInicio> findAll();
    
}