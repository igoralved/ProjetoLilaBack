package com.db.jogo.service;

import java.util.Optional;

import com.db.jogo.dto.SalaResponse;
import com.db.jogo.exception.JogoInvalidoException;
import com.db.jogo.model.CartaDoJogo;
import com.db.jogo.model.Jogador;
import com.db.jogo.model.Sala;


public interface WebSocketService {
     SalaResponse criarJogo(Jogador jogador) throws JogoInvalidoException;

     SalaResponse conectarJogo(Jogador jogador, String hash) throws JogoInvalidoException;

     Jogador criarJogador(Jogador jogador);
     
     CartaDoJogo criarCartaDoJogo();
     
     Optional<Sala> comprarCartaDoJogo(Sala salaFront);

}
