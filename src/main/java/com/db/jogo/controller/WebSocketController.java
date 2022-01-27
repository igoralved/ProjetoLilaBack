package com.db.jogo.controller;

import javax.validation.Valid;

import com.db.jogo.dto.SalaRequest;
import com.db.jogo.dto.SalaResponse;
import com.db.jogo.exception.JogoInvalidoException;
import com.db.jogo.exception.JsonInvalidoException;
import com.db.jogo.model.Jogador;
import com.db.jogo.model.Sala;
import com.db.jogo.service.WebSocketServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(path = "/api")
public class WebSocketController {

    private final WebSocketServiceImpl webSocketServiceImpl;

    @PostMapping("/iniciar")
    public ResponseEntity<SalaResponse> iniciarJogo(@RequestBody @Valid Jogador jogador) throws JogoInvalidoException {
        log.info("Requisição para iniciar jogo {}", jogador);
        SalaResponse sala = this.webSocketServiceImpl.criarJogo(jogador);
        return new ResponseEntity<>(sala, HttpStatus.OK);
    }

    @PutMapping("/conectar")
    public ResponseEntity<SalaResponse> conectar(@RequestBody SalaRequest request) throws JogoInvalidoException {
        log.info("Requisição da conexão: {}", request);
        SalaResponse sala = webSocketServiceImpl.conectarJogo(request.getJogador(), request.getHash());

        if (sala.getSala() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Integer i = webSocketServiceImpl.getQuantidadeJogadores(request.getHash());
        
        try {
            webSocketServiceImpl.sendSala(sala.getSala()); // envia a sala para o websocket
            return new ResponseEntity<>(sala,HttpStatus.OK);
        } catch (JsonInvalidoException e) {
            System.err.println("Não foi possível criar o JSON da sala.");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/iniciarpartida")
    public ResponseEntity<Sala> updateSala(@RequestBody Sala sala) {
        try {
            webSocketServiceImpl.sendSala(sala); // envia a sala para o websocket
            return new ResponseEntity<>(sala,HttpStatus.OK);
        } catch (JsonInvalidoException e) {
            System.err.println("Não foi possível criar o JSON da sala.");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}