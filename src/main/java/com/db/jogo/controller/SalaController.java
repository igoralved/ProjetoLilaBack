package com.db.jogo.controller;

import java.util.Optional;

import com.db.jogo.model.Sala;
import com.db.jogo.service.SalaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/sala")
public class SalaController {

    private final SalaService salaService;

    @Autowired
    public SalaController(SalaService salaService){
        this.salaService = salaService;
    }

    @PostMapping
    public ResponseEntity<Sala> criarSala(@RequestBody Sala sala, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(sala, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(salaService.saveSala(sala), HttpStatus.CREATED);
    }

    @GetMapping("/{hash}")
    public ResponseEntity<Sala> encontrarSalaPorHash(@PathVariable String hash) {
        Optional<Sala> sala = salaService.findSalaByHash(hash);
        return sala.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}