package com.db.jogo.repository;


import com.db.jogo.model.Jogador;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface JogadorRepository extends CrudRepository<Jogador, UUID> {


}
