package com.db.jogo.service;

import java.util.Optional;
import java.util.UUID;

import com.db.jogo.model.Admin;
import com.db.jogo.repository.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl  implements AdminService{

    private AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(
            AdminRepository adminRepository
    ){
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin findById(UUID id) throws DataAccessException {
        Optional<Admin> admin = this.adminRepository.findById(id);
            if (admin == null){
                return null;
            }
            return admin.get();
    }

    @Override
    public Admin saveAdmin(Admin admin) throws DataAccessException {
        return adminRepository.save(admin);
    }

    @Override
    public Iterable<Admin> findAll() throws DataAccessException {
        
        return adminRepository.findAll();
    }

    @Override
    public Admin findBySenha(String senha) throws DataAccessException {
        Admin admin = this.adminRepository.findBySenha(senha);
        if (admin == null){
            return null;
        }
        return admin;
}
        
    
}
