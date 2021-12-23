package com.db.jogo.service;

import com.db.jogo.model.Admin;
import com.db.jogo.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.UUID;

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
}
