package com.db.jogo.service;

import com.db.jogo.model.Baralho;
import com.db.jogo.model.Categoria;
import org.springframework.dao.DataAccessException;

public interface CategoriaService {
    Categoria findById(long id) throws DataAccessException;
    Categoria saveCategoria(Categoria categoria) throws DataAccessException;
    Iterable<Categoria> findAll() throws DataAccessException;
}
