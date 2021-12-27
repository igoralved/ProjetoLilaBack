package com.db.jogo.repository;

import com.db.jogo.model.Admin;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface AdminRepository extends CrudRepository<Admin , UUID> {
    Admin findBySenha(String senha) throws DataAccessException;
}
