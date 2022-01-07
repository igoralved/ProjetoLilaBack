package com.db.jogo.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.db.jogo.model.Jogador;
import com.db.jogo.repository.JogadorRepository;

@Service
public class JogadorServiceImpl implements JogadorService {

	private JogadorRepository jogadorRepository;

	@Autowired
	public JogadorServiceImpl(JogadorRepository jogadorRepository) {
		this.jogadorRepository = jogadorRepository;

	}

	@Override
	public Optional<Jogador> findById(UUID id) throws DataAccessException {
		return jogadorRepository.findById(id);
	}

	@Override
	public Jogador saveJogador(Jogador jogador) throws DataAccessException {
		return jogadorRepository.save(jogador);

	}

}
