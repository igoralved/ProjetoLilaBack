package com.db.jogo.repository;


import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.db.jogo.model.Jogador;

@Repository
public interface JogadorRepository extends CrudRepository<Jogador, UUID> {
}
