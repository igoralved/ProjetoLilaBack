package com.db.jogo.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.db.jogo.model.CartaInicio;
import com.db.jogo.repository.CartaInicioRepository;

@Service
public class CartaInicioServiceImpl implements CartaInicioService{

    private CartaInicioRepository cartaInicioRepository;

    @Autowired
    public CartaInicioServiceImpl(CartaInicioRepository cartaInicioRepository) {
        this.cartaInicioRepository = cartaInicioRepository;
    }

    @Override
    public CartaInicio saveCartaInicio(CartaInicio cartaInicio) throws DataAccessException {
        return cartaInicioRepository.save(cartaInicio);
    }

    @Override
    public Iterable<CartaInicio> findAll() throws DataAccessException {
        return cartaInicioRepository.findAll();
    }

    @Override
    public CartaInicio findById(UUID id) throws DataAccessException {
        Optional<CartaInicio> cartaInicio = cartaInicioRepository.findById(id);
        if(cartaInicio == null) {
            return null;
        }
        return cartaInicio.get();
    }

}
