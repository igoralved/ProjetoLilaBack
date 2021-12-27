package com.db.jogo.service;

import com.db.jogo.model.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private AdminService admService;

    public Boolean verificaSenha(String senha) throws IllegalArgumentException{
        Boolean result = false;
        try {
            if(senha == null){
                result = false;
            }else {
                Admin adminFind = admService.findBySenha(senha);
                result = adminFind != null;
            }
        }catch(NullPointerException e){
            result = false;
        }

        return result;
    }
}
