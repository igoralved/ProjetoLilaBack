package com.db.jogo.repository;

import java.util.UUID;

import com.db.jogo.model.Admin;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin , UUID> {
    Admin findBySenha(String senha) throws DataAccessException;
}
