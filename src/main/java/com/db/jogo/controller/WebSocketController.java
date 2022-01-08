package com.db.jogo.controller;

import com.db.jogo.dto.SalaRequest;
import com.db.jogo.exception.JogoInvalidoException;
import com.db.jogo.model.Jogador;
import com.db.jogo.model.Sala;
import com.db.jogo.service.SalaService;
import com.db.jogo.service.WebSocketService;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@CrossOrigin
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(path = "/api")
public class WebSocketController {

    private final WebSocketService webSocketService;
    private final SalaService salaService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/iniciar")
    public ResponseEntity<Sala> iniciarJogo(@RequestBody Jogador jogador) {
        log.info("Requisição para iniciar jogo {}", jogador);
        return ResponseEntity.ok(webSocketService.criarJogo(jogador));
    }

    @PostMapping("/conectar")
    public ResponseEntity<Sala> conectar(@RequestBody SalaRequest request) throws JogoInvalidoException {
        log.info("Requisição da conexão: {}", request);
        return ResponseEntity.ok(webSocketService.conectarJogo(request.getJogador(), request.getHash()).get());
    }

    @PostMapping("/jogo")
    public ResponseEntity<Sala> jogada(@RequestBody Sala request) {
        log.info("Jogada: {}", request);
        Sala sala = salaService.jogada(request);
        simpMessagingTemplate.convertAndSend("/topic/" + sala.getHash(), sala);
        return ResponseEntity.ok(sala);
    }
}