package com.db.jogo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.db.jogo.model.Baralho;
import com.db.jogo.repository.BaralhoRepository;

@Service
public class BaralhoServiceImpl implements BaralhoService {

	private BaralhoRepository baralhoRepository;

	@Autowired
	public BaralhoServiceImpl(BaralhoRepository baralhoRepository) {
		this.baralhoRepository = baralhoRepository;
	}


	@Override
	public Optional<Baralho> findByCodigo(String codigo) throws DataAccessException {
		Optional<Baralho> baralho = this.baralhoRepository.findByCodigo(codigo);
		return baralho;
	}


	
	@Override
	public Baralho findById(String id) throws DataAccessException {
		Optional<Baralho> baralho = this.baralhoRepository.findById(id);
		if(baralho == null) {
			return null;
		}
		return baralho.get();
	}
	

	@Override
	public Baralho saveBaralho(Baralho baralho) throws DataAccessException {
		return baralhoRepository.save(baralho);
	}

	@Override
	public Iterable<Baralho> findAll() throws DataAccessException {
		return baralhoRepository.findAll();
	}

}


