package com.db.jogo.controller;

import com.db.jogo.model.CartaObjetivo;
import com.db.jogo.service.CartaObjetivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/cartaobjetivo")
@RestController
public class CartaObjetivoController {

    @Autowired
    private CartaObjetivoService cartaObjetivoService;


    @GetMapping
    public ResponseEntity<?> findCartaObjetivo() {
        List<CartaObjetivo> listaCartaObjetivo = cartaObjetivoService.findAll();
        if(!listaCartaObjetivo.isEmpty()){
            return new ResponseEntity<>(listaCartaObjetivo, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(cartaObjetivoService.findAll(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartaObjetivo> findCartaObjetivoById(@PathVariable UUID id) {
        Optional<CartaObjetivo> cartaObjetivo = cartaObjetivoService.findById(id);
        return cartaObjetivo
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<CartaObjetivo> saveCartaObjetivo(@RequestBody CartaObjetivo cartaObjetivo, BindingResult bindingResult) {
        if(bindingResult.hasErrors() || cartaObjetivo == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(cartaObjetivoService.saveCartaObjetivo(cartaObjetivo), HttpStatus.CREATED);
    }
}
