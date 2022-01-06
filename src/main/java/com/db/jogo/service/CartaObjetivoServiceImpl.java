package com.db.jogo.service;

import com.db.jogo.model.CartaObjetivo;
import com.db.jogo.repository.CartaObjetivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CartaObjetivoServiceImpl implements CartaObjetivoService {

    private final CartaObjetivoRepository cartaObjetivoRepository;

    @Autowired
    public CartaObjetivoServiceImpl(CartaObjetivoRepository cartaObjetivoRepository) {
        this.cartaObjetivoRepository = cartaObjetivoRepository;
    }

    @Override
    public Optional<CartaObjetivo> findById(UUID id) throws DataAccessException {
        return cartaObjetivoRepository.findById(id);
    }

    @Override
    public Iterable<CartaObjetivo> findAll() throws DataAccessException {
        return cartaObjetivoRepository.findAll();
    }

    @Override
    public CartaObjetivo saveCartaObjetivo(CartaObjetivo cartaObjetivo) throws DataAccessException {
        return cartaObjetivoRepository.save(cartaObjetivo);
    }
}
