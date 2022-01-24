package com.db.jogo.service;

import com.db.jogo.model.CartaInicio;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartaInicioService {

    Optional<CartaInicio> findById(UUID id) throws DataAccessException;
    CartaInicio saveCartaInicio(CartaInicio cartaInicio) throws DataAccessException;
    ArrayList<CartaInicio> findAllCartaInicio() throws DataAccessException;
    Iterable<CartaInicio> findAll();
    
}