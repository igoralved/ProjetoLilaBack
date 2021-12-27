package com.db.jogo.controller;

import com.db.jogo.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Boolean> verificaSenha(@RequestParam String senha, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Boolean>(loginService.verificaSenha(senha), HttpStatus.OK);
    }
}
