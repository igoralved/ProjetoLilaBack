package com.db.jogo.controller;


import com.db.jogo.model.CartaDoJogo;
import com.db.jogo.repository.CartaDoJogoRepository;

import com.db.jogo.service.CartaDoJogoService;
import com.db.jogo.service.CartaDoJogoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/cartadojogo")
public class CartaDoJogoController {


    private  CartaDoJogoService cartaDoJogoService;
    private ArrayList<CartaDoJogo> cartaDoJogoArraylist = new ArrayList<>();

    @Autowired
    public CartaDoJogoController(CartaDoJogoService cartaInicioService){
        this.cartaDoJogoService = cartaInicioService;
    }

    @PostMapping
    public ResponseEntity<CartaDoJogo> saveCartaDoJogo(@RequestBody CartaDoJogo cartaDoJogo, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(cartaDoJogo, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(cartaDoJogoService.saveCartaDoJogo(cartaDoJogo), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> findAllCartaInicio() {

        List<CartaDoJogo> listaCartaDoJogo = cartaDoJogoService.findAll();
        if(!listaCartaDoJogo.isEmpty()){
            return new ResponseEntity<>(listaCartaDoJogo, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(cartaDoJogoService.findAll(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartaDoJogo> findByIdcartaInicio(@PathVariable UUID id) {
        Optional<CartaDoJogo> cartaInicio = Optional.empty();
        cartaInicio = cartaDoJogoService.findById(id);
        if (cartaInicio.isEmpty()){
            return new ResponseEntity<CartaDoJogo>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<CartaDoJogo>(cartaInicio.get(), HttpStatus.OK);

    }


}