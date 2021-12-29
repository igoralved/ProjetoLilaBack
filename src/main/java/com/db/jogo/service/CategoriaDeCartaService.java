package com.db.jogo.service;

import com.db.jogo.model.CategoriaDeCarta;
import org.springframework.dao.DataAccessException;

import java.util.UUID;

public interface CategoriaService {
    CategoriaDeCarta findById(UUID id) throws DataAccessException;
    CategoriaDeCarta saveCategoria(CategoriaDeCarta categoria) throws DataAccessException;
    Iterable<CategoriaDeCarta> findAll() throws DataAccessException;
}
