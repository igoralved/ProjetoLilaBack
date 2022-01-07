package com.db.jogo.service;

import com.db.jogo.model.Jogador;
import org.springframework.dao.DataAccessException;

import java.util.Optional;
import java.util.UUID;

public interface JogadorService {
    Optional<Jogador> findById(UUID id) throws DataAccessException;
    Jogador saveJogador(Jogador jogador) throws DataAccessException;
}
