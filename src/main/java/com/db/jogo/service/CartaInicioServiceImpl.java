package com.db.jogo.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.db.jogo.model.CartaInicio;
import com.db.jogo.repository.CartaInicioRepository;

@Service
public class CartaInicioServiceImpl implements CartaInicioService {

	private CartaInicioRepository cartaInicioRepository;

	@Autowired
	public CartaInicioServiceImpl(
			CartaInicioRepository cartaInicioRepository /* , CartaInicioController cartaInicioController */ ) {
		this.cartaInicioRepository = cartaInicioRepository;

	}

	@Override
	public Optional<CartaInicio> findById(UUID id) throws DataAccessException {
		return cartaInicioRepository.findById(id);
	}

	@Override
	public CartaInicio saveCartaInicio(CartaInicio cartaInicio) throws DataAccessException {
		return cartaInicioRepository.save(cartaInicio);
	}

	@Override
	public ArrayList<CartaInicio> findAllCartaInicio() throws DataAccessException {
		return (ArrayList<CartaInicio>) cartaInicioRepository.findAll();
	}

	@Override
	public Iterable<CartaInicio> findAll() {

		return cartaInicioRepository.findAll();
	}

}