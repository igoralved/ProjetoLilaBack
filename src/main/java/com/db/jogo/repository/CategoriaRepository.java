package com.db.jogo.repository;

import com.db.jogo.model.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface CategoriaRepository  extends CrudRepository<Categoria, Long> {
}
