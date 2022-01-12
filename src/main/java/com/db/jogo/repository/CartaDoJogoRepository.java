package com.db.jogo.repository;

import com.db.jogo.model.CartaDoJogo;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartaDoJogoRepository extends CrudRepository<CartaDoJogo, UUID> {

}
