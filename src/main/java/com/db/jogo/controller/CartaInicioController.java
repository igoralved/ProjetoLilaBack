package com.db.jogo.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.db.jogo.model.CartaInicio;
import com.db.jogo.service.CartaInicioService;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/cartainicio")
public class CartaInicioController {

    @Autowired
    private CartaInicioService cartaInicioService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Iterable<CartaInicio>> findCartaObjetivo() {
        return new ResponseEntity<Iterable<CartaInicio>>(cartaInicioService.findAll(), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<CartaInicio> saveCartaInicio(@RequestBody CartaInicio cartaInicio, BindingResult bindingResult){
        if(bindingResult.hasErrors() || cartaInicio == null || cartaInicio.getId() == null) {
            return new ResponseEntity<CartaInicio>(cartaInicio, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<CartaInicio>(cartaInicioService.saveCartaInicio(cartaInicio), HttpStatus.CREATED);
    }

}
