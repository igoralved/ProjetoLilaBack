package com.db.jogo.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.jogo.dto.SalaRequest;
import com.db.jogo.dto.SalaResponse;
import com.db.jogo.exception.JogoInvalidoException;
import com.db.jogo.model.Jogador;
import com.db.jogo.model.Sala;
import com.db.jogo.service.WebSocketServiceImpl;

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

	@PostMapping("/conectar")
	public ResponseEntity<SalaResponse> conectar(@RequestBody SalaRequest request) throws JogoInvalidoException {
		log.info("Requisição da conexão: {}", request);
		SalaResponse sala = webSocketServiceImpl.conectarJogo(request.getJogador(), request.getHash());

		if (sala.getSala() == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(sala, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@PutMapping("/jogada/comprarcarta")
	public ResponseEntity<?> comprarCartaDoJogo(@RequestBody Sala sala, BindingResult bindingResult) throws JogoInvalidoException {

		if (bindingResult.hasErrors() || sala == null || sala.getHash() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		try {
			Optional<Sala> salaParaAtualizar = this.webSocketServiceImpl.comprarCartaDoJogo(sala);
			
			if(salaParaAtualizar.isPresent()) {
				return new ResponseEntity<>("salaParaAtualizar.get().getdado()", HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}