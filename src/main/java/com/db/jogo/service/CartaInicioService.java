package com.db.jogo.service;

import com.db.jogo.model.CartaInicio;
import com.db.jogo.repository.CartaInicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CartaInicioService {
    private CartaInicioRepository cartaInicioRepository;

    @Autowired
    public CartaInicioService(CartaInicioRepository cartaInicioRepository) {
        this.cartaInicioRepository = cartaInicioRepository;
    }

    public CartaInicio findByIdCartaInicio(UUID id) {
        return cartaInicioRepository.getById(id);
    }
    public CartaInicio saveCartaInicio(CartaInicio cartaInicio){
        return cartaInicioRepository.save(cartaInicio);
    }

}

