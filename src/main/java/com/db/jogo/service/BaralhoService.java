package com.db.jogo.service;

import com.db.jogo.model.Baralho;

import org.springframework.dao.DataAccessException;

import java.util.Optional;

public interface BaralhoService {

	Optional<Baralho> findByCodigo(String codigo) throws DataAccessException;
	Baralho saveBaralho(Baralho baralho) throws DataAccessException;
	Iterable<Baralho> findAll() throws DataAccessException;
}
