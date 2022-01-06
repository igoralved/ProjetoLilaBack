package com.db.jogo.controller;

import com.db.jogo.model.CartaObjetivo;
import com.db.jogo.service.CartaObjetivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/CartaObjetivo")
@RestController
public class CartaObjetivoController {

    @Autowired
    private CartaObjetivoService cartaObjetivoService;


    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Iterable<CartaObjetivo>> findCartaObjetivo() {
        return new ResponseEntity<Iterable<CartaObjetivo>>(cartaObjetivoService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<CartaObjetivo> findCartaObjetivoById(UUID Id) {
        return new ResponseEntity<CartaObjetivo>(cartaObjetivoService.findById(Id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<CartaObjetivo> saveCartaObjetivo(@RequestBody CartaObjetivo cartaObjetivo, BindingResult bindingResult){
        if(bindingResult.hasErrors() || (cartaObjetivo.getPontos() == (null) || (cartaObjetivo.getCategoria() == null) || (cartaObjetivo.getDescricao() == null))) {
            return new ResponseEntity<CartaObjetivo>(cartaObjetivo, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<CartaObjetivo>(cartaObjetivoService.saveCartaObjetivo(cartaObjetivo), HttpStatus.CREATED);
    }

}
