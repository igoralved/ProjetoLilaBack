package com.db.jogo.service;

import java.util.UUID;

import com.db.jogo.model.Admin;

import org.springframework.dao.DataAccessException;

public interface AdminService {

    Admin findById(UUID id) throws DataAccessException;
    Admin findBySenha(String senha) throws DataAccessException;
    Admin saveAdmin(Admin admin) throws DataAccessException;
    Iterable<Admin> findAll() throws DataAccessException;

}
