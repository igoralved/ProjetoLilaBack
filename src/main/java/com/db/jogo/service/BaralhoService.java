package com.db.jogo.service;

import com.db.jogo.model.Baralho;

import org.springframework.dao.DataAccessException;

public interface BaralhoService {

	Baralho findById(String id) throws DataAccessException;
	Baralho saveBaralho(Baralho baralho) throws DataAccessException;
	Iterable<Baralho> findAll() throws DataAccessException;
}
