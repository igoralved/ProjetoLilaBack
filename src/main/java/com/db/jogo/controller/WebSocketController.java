package com.db.jogo.controller;

import com.db.jogo.dto.SalaRequest;
import com.db.jogo.exception.JogoInvalidoException;
import com.db.jogo.model.Jogador;
import com.db.jogo.model.Sala;
import com.db.jogo.service.SalaService;
import com.db.jogo.service.WebSocketServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(path = "/api")
public class WebSocketController {

    private final WebSocketServiceImpl webSocketServiceImpl;
    private final SalaService salaService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/iniciar")
    public ResponseEntity<Sala> iniciarJogo(@RequestBody Jogador jogador) {
        log.info("Requisição para iniciar jogo {}", jogador);
        Sala sala = this.webSocketServiceImpl.criarJogo(jogador);
        return new ResponseEntity<>(sala, HttpStatus.OK);
    }

    @PostMapping("/conectar")
    public ResponseEntity<Sala> conectar(@RequestBody SalaRequest request) throws JogoInvalidoException {
        log.info("Requisição da conexão: {}", request);
        return ResponseEntity.ok(webSocketServiceImpl.conectarJogo(request.getJogador(), request.getHash()).get());
    }


}