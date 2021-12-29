package com.db.jogo.repository;

import com.db.jogo.model.CategoriaDeCarta;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CategoriaRepository  extends CrudRepository<CategoriaDeCarta, UUID> {
}
