package com.db.jogo.service;

import com.db.jogo.model.CartaDoJogo;
import com.db.jogo.repository.CartaDoJogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartaDoJogoServiceImpl implements CartaDoJogoService {

	private final CartaDoJogoRepository cartaDoJogoRepository;

	@Autowired
	public CartaDoJogoServiceImpl(CartaDoJogoRepository cartaDoJogoRepository) {

		this.cartaDoJogoRepository = cartaDoJogoRepository;
	}

	@Override
	public Optional<CartaDoJogo> findById(UUID id) throws DataAccessException {

		return cartaDoJogoRepository.findById(id);
	}

	@Override
	public CartaDoJogo saveCartaDoJogo(CartaDoJogo cartaDoJogo) throws DataAccessException {

		return cartaDoJogoRepository.save(cartaDoJogo);
	}

	@Override
	public List<CartaDoJogo> findAll() throws DataAccessException {

		return (List<CartaDoJogo>) cartaDoJogoRepository.findAll();
	}
}
