package com.db.jogo.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.db.jogo.model.CartaObjetivo;
import com.db.jogo.model.Jogador;
import com.db.jogo.model.Sala;
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



	public Optional<Jogador> atualizarJogador(Jogador jogador) throws IllegalArgumentException {

		Optional<Jogador> jogadorParaAtualizar = Optional.empty();

		try {

			jogadorParaAtualizar = jogadorRepository.findById(jogador.getId());

			if (jogadorParaAtualizar.isPresent()) {

				jogadorParaAtualizar.get().setBonusCoracaoGra(jogador.getBonusCoracaoGra());
				jogadorParaAtualizar.get().setBonusCoracaoPeq(jogador.getBonusCoracaoPeq());
				jogadorParaAtualizar.get().setCoracaoGra(jogador.getCoracaoGra());
				jogadorParaAtualizar.get().setCoracaoPeq(jogador.getCoracaoPeq());
				jogadorParaAtualizar.get().setListaDeCartas(jogador.getListaDeCartas());
				jogadorParaAtualizar.get().setListaDeObjetivos(jogador.getListaDeObjetivos());
				jogadorParaAtualizar.get().setNome(jogador.getNome());
				jogadorParaAtualizar.get().setPontos(jogador.getPontos());

				jogadorRepository.save(jogadorParaAtualizar.get());
				return jogadorParaAtualizar;
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("Impossível fazer atualização do objeto passado! ", e);
		}
		return jogadorParaAtualizar;
	}

	@Override
	public int totalJogadores() {
		List<Jogador> lista = (List<Jogador>) this.jogadorRepository.findAll();
		if(lista.size() == 0) {
			return 0;
		}
		return lista.size();
	}

	@Override
	public Iterable<Jogador> findAll() {
		// TODO Auto-generated method stub
		return this.jogadorRepository.findAll();
	}


}
