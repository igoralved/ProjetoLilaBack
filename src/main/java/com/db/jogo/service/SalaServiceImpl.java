package com.db.jogo.service;

import java.util.Optional;

import com.db.jogo.model.Sala;
import com.db.jogo.repository.SalaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class SalaServiceImpl implements SalaService {

	private SalaRepository salaRepository;

	@Autowired
	public SalaServiceImpl(SalaRepository salaRepository) {
		this.salaRepository = salaRepository;
	}


	@Override
	public Optional<Sala> findSalaByHash(String hash) throws DataAccessException {
		return salaRepository.findSalaByHash(hash);
	}

	@Override
	public Sala saveSala(Sala sala) throws DataAccessException {
		return salaRepository.save(sala);
	}
}


