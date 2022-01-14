package com.db.jogo.service;

import com.db.jogo.model.CartaDoJogo;
import com.db.jogo.repository.CartaDoJogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CartaDoJogoServiceImpl implements CartaDoJogoService{

    private CartaDoJogoRepository cartaDoJogoRepository;

    @Autowired
    public CartaDoJogoServiceImpl(CartaDoJogoRepository cartaDoJogoRepository) {
        this.cartaDoJogoRepository = cartaDoJogoRepository;
    }

    @Override
    public CartaDoJogo findById(UUID id) throws DataAccessException {
        Optional<CartaDoJogo> cartaDoJogo = this.cartaDoJogoRepository.findById(id);
        if(cartaDoJogo == null) {
            return null;
        }
        return cartaDoJogo.get();
    }

    @Override
    public CartaDoJogo saveCartaDoJogo(CartaDoJogo cartaDoJogo) throws DataAccessException {
        return cartaDoJogoRepository.save(cartaDoJogo);
    }

    @Override
    public Iterable<CartaDoJogo> findAll() throws DataAccessException {
        return cartaDoJogoRepository.findAll();
    }
}
