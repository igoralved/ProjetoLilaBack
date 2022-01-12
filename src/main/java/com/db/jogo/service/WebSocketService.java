package com.db.jogo.service;

import com.db.jogo.exception.JogoInvalidoException;
import com.db.jogo.model.Jogador;
import com.db.jogo.model.Sala;

import java.util.Optional;

public interface WebSocketService {
     Sala criarJogo(Jogador jogador) throws JogoInvalidoException;
     Optional<Sala> conectarJogo(Jogador jogador, String hash) throws JogoInvalidoException;
     Jogador criaJogador(Jogador jogador);
}
