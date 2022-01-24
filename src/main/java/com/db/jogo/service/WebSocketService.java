package com.db.jogo.service;

import com.db.jogo.dto.SalaResponse;
import com.db.jogo.exception.JogoInvalidoException;
import com.db.jogo.model.Jogador;

public interface WebSocketService {
     SalaResponse criarJogo(Jogador jogador) throws JogoInvalidoException;

     SalaResponse conectarJogo(Jogador jogador, String hash) throws JogoInvalidoException;

     Jogador criarJogador(Jogador jogador);
}
