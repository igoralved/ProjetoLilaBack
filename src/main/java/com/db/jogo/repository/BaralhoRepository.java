package com.db.jogo.repository;

import org.springframework.data.repository.CrudRepository;

import com.db.jogo.model.Baralho;

import java.util.Optional;

public interface BaralhoRepository extends CrudRepository<Baralho, String> {

    Optional<Baralho> findByCodigo(String codigo);

}
