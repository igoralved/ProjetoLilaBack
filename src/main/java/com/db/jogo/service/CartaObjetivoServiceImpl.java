package com.db.jogo.service;

import com.db.jogo.model.CartaObjetivo;
import com.db.jogo.repository.CartaObjetivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CartaObjetivoServiceImpl {

    private final CartaObjetivoRepository cartaObjetivoRepository;

    @Autowired
    public CartaObjetivoServiceImpl(CartaObjetivoRepository cartaObjetivoRepository) {
        this.cartaObjetivoRepository = cartaObjetivoRepository;
    }

    public CartaObjetivo findById(UUID id) throws DataAccessException {
        Optional<CartaObjetivo> cartaObjetivo = this.cartaObjetivoRepository.findById(id);
        return cartaObjetivo.orElse(null);
    }

    public Iterable<CartaObjetivo> findAll() throws DataAccessException {
        return cartaObjetivoRepository.findAll();
    }

    public CartaObjetivo saveCartaObjetivo(CartaObjetivo cartaObjetivo) throws DataAccessException {
        return cartaObjetivoRepository.save(cartaObjetivo);
    }
}
