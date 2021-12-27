package com.db.jogo.service;

import com.db.jogo.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LoginService {

    @Autowired
    private AdminService admService;

    public Boolean verificaSenha(Admin admin) throws IllegalArgumentException{
        Boolean result = false;
        try {
            if(admin.getSenha() == null){
                result = false;
            }else {
                Admin adminFind = admService.findById(admin.getId());
                result = adminFind.getSenha().equals(admin.getSenha());
            }
        }catch(NullPointerException e){
            result = false;
        }

        return result;
    }
}
