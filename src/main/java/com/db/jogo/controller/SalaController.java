package com.db.jogo.controller;

import com.db.jogo.model.Admin;
import com.db.jogo.model.Sala;
import com.db.jogo.service.AdminService;

import com.db.jogo.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/sala")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @PostMapping
    public ResponseEntity<Sala> criarSala(@RequestBody Sala sala, BindingResult bindingResult) {
        if(bindingResult.hasErrors() || (sala.getHash().isEmpty())){
            return new ResponseEntity<>(sala, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(salaService.saveSala(sala), HttpStatus.CREATED);
    }

    @GetMapping("{/hash}")
    public ResponseEntity<Sala> findSala(@PathVariable String hash) {
        Optional<Sala> sala = salaService.findSalaByHash(hash);
        if (sala.isPresent()) {
            return new ResponseEntity<>(sala.get(), HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
