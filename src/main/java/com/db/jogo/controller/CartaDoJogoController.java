package com.db.jogo.controller;

import com.db.jogo.model.CartaDoJogo;
import com.db.jogo.repository.CartaDoJogoRepository;
import com.db.jogo.service.CartaDoJogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/cartadojogo")
public class CartaDoJogoController {

    @Autowired
    private CartaDoJogoService cartaDoJogoService;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<CartaDoJogo> saveCartaDoJogo(@RequestBody CartaDoJogo cartaDoJogo, BindingResult bindingResult ){
        if (bindingResult.hasErrors() || cartaDoJogo.getTipo() == null || cartaDoJogo.getCategoria() == null || cartaDoJogo.getFonte() == null || cartaDoJogo.getPontos() == null || cartaDoJogo.getTexto() == null){
            return new ResponseEntity<>(cartaDoJogo, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(cartaDoJogoService.saveCartaDoJogo(cartaDoJogo), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Iterable<CartaDoJogo>> findCartaDoJogo() {
        return new ResponseEntity<>(cartaDoJogoService.findAll(), HttpStatus.OK);
    }

}
