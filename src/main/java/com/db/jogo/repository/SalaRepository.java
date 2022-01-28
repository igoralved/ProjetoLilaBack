package com.db.jogo.repository;

import java.util.Optional;
import java.util.UUID;

import com.db.jogo.model.Sala;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepository extends CrudRepository<Sala, UUID> {
    Optional<Sala> findSalaByHash(String hash) throws DataAccessException;

}
