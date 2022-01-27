package com.db.jogo.service;


import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.DataAccessException;


import com.db.jogo.model.Jogador;

public interface JogadorService {
	
	Optional<Jogador> findById(UUID id) throws DataAccessException;
	Jogador saveJogador(Jogador jogador) throws DataAccessException;
}


