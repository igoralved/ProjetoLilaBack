package com.db.jogo.service;

import java.util.List;
import java.util.Optional;

import com.db.jogo.model.Jogador;
import com.db.jogo.model.Sala;
import com.db.jogo.repository.SalaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class SalaServiceImpl implements SalaService {

	private final SalaRepository salaRepository;

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

	/**
	 * Este método executa a jogada
	 * @param sala - essa sala é o estado inicial do jogo
	 * @return retorna um novo estado do jogo após a jogada computada
	 * @throws DataAccessException
	 */
	@Override
	public Sala jogada(Sala sala) throws DataAccessException {
		/*TODO: AQUI VAI A LÓGICA DO JOGO*/
		return sala;
	}


	@Override
	public Integer totalJogadores(String hash) {
		Optional<Sala> optsala = salaRepository.findSalaByHash(hash);
		if(optsala.isEmpty()) {
			return 0;
		}
		Sala sala = optsala.get();
		List<Jogador> lista = sala.getJogadores();
		return lista.size();
	}


	@Override
	public Jogador findFirst(String hash) {
		Optional<Sala> sala = salaRepository.findSalaByHash(hash);
		List<Jogador> lista = sala.get().getJogadores();
		if(lista.isEmpty()) {
			return null;
		}return lista.get(0);
	}
}


