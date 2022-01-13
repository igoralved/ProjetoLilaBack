package com.db.jogo.service;

import com.db.jogo.model.CartaObjetivo;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartaObjetivoService {
    CartaObjetivo saveCartaObjetivo(CartaObjetivo cartaObjetivo) throws DataAccessException;
    List<CartaObjetivo> findAll() throws DataAccessException;
    Optional<CartaObjetivo> findById(UUID id) throws DataAccessException;
}
