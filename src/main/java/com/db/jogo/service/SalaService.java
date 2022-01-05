package com.db.jogo.service;

import com.db.jogo.model.Admin;
import com.db.jogo.model.Sala;
import org.springframework.dao.DataAccessException;

import java.util.Optional;
import java.util.UUID;

public interface SalaService {
    Optional<Sala> findSalaByHash(String hash) throws DataAccessException;
    Sala saveSala(Sala sala) throws DataAccessException;
}
