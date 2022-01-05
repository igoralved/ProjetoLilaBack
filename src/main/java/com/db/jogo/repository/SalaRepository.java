package com.db.jogo.repository;

import com.db.jogo.model.Admin;
import com.db.jogo.model.Sala;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SalaRepository extends CrudRepository<Sala, UUID> {
    Optional<Sala> findSalaByHash(String hash) throws DataAccessException;
}
