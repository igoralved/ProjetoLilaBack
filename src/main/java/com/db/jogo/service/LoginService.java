package com.db.jogo.service;

import com.db.jogo.model.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private AdminService admService;

    public Boolean verificaSenha(String senha) throws IllegalArgumentException{
        Boolean resultado = false;
        try {
            if(senha == null){
                resultado = false;
            }else {
                Admin adminEncontrado = admService.findBySenha(senha);
                resultado = adminEncontrado != null;
            }
        }catch(NullPointerException e){
            resultado = false;
        }

        return resultado;
    }
}
