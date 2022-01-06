package com.db.jogo.controller;

import com.db.jogo.model.Sala;
import com.db.jogo.service.WebSocketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(path = "/api")
public class WebSocketController {

    @Autowired
    private WebSocketService service;

    @PutMapping
    public ResponseEntity<Sala> execute(@RequestBody Sala sala) {
        service.jogada(sala);

        return new ResponseEntity<Sala>(HttpStatus.OK);
}
}