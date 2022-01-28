package com.db.jogo.service;

import java.util.Optional;

import com.db.jogo.model.Sala;


import com.db.jogo.util.Dado;

import org.springframework.dao.DataAccessException;

public interface SalaService {
    Optional<Sala> findSalaByHash(String hash) throws DataAccessException;
    Sala saveSala(Sala sala) throws DataAccessException;
    Sala jogada(Sala sala) throws DataAccessException;

}
