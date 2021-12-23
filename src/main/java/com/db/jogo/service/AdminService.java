package com.db.jogo.service;

import com.db.jogo.model.Admin;
import org.springframework.dao.DataAccessException;

import java.util.UUID;

public interface AdminService {

    Admin findById(UUID id) throws DataAccessException;
    Admin saveAdmin(Admin admin) throws DataAccessException;

}
