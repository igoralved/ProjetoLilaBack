package com.db.jogo.service;

import com.db.jogo.controller.CartaInicioController;
import com.db.jogo.controller.CategoriaDeCartaController;
import com.db.jogo.model.CartaInicio;
import com.db.jogo.repository.CartaInicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartaInicioServiceImpl implements CartaInicioService{

    private CartaInicioRepository cartaInicioRepository;
 //   private CartaInicioController cartaInicioController;

    @Autowired
    public CartaInicioServiceImpl(CartaInicioRepository cartaInicioRepository /*, CartaInicioController cartaInicioController*/ ){
        this.cartaInicioRepository = cartaInicioRepository;
       // this.cartaInicioController = cartaInicioController;
    }

    @Override
    public Optional<CartaInicio> findById(UUID id) throws DataAccessException {
        return cartaInicioRepository.findById(id);
    }



    @Override
    public CartaInicio saveCartaInicio(CartaInicio cartaInicio) throws DataAccessException{
        return cartaInicioRepository.save(cartaInicio);
    }

    @Override
    public List<CartaInicio> findAllCartaInicio() throws DataAccessException {
        return (List<CartaInicio>) cartaInicioRepository.findAll();
    }


}
