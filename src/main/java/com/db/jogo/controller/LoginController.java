package com.db.jogo.controller;

import com.db.jogo.model.Admin;
import com.db.jogo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Boolean> verificaSenha(@RequestBody Admin admin, BindingResult bindingResult){
        if(bindingResult.hasErrors() || (admin.getSenha() == null)){
            return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Boolean>(loginService.verificaSenha(admin), HttpStatus.OK);
    }
}
