package com.db.jogo.controller;

<<<<<<< HEAD

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
=======
import javax.validation.Valid;

import com.db.jogo.dto.SalaRequest;
import com.db.jogo.dto.SalaResponse;
import com.db.jogo.exception.JogoInvalidoException;
import com.db.jogo.model.Jogador;
import com.db.jogo.service.WebSocketServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
>>>>>>> origin/AlteraçãoBanco-Models
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD
import com.db.jogo.dto.SalaRequest;
import com.db.jogo.dto.SalaResponse;
import com.db.jogo.exception.JogoInvalidoException;
import com.db.jogo.model.Jogador;
import com.db.jogo.model.Sala;
import com.db.jogo.service.WebSocketServiceImpl;


=======
import lombok.AllArgsConstructor;
>>>>>>> origin/AlteraçãoBanco-Models
import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@Slf4j
<<<<<<< HEAD
=======
@AllArgsConstructor
>>>>>>> origin/AlteraçãoBanco-Models
@RestController
@RequestMapping(path = "/api")
public class WebSocketController {

<<<<<<< HEAD

	private final WebSocketServiceImpl webSocketServiceImpl;
	
	   @Autowired
	    public WebSocketController(WebSocketServiceImpl webSocketServiceImpl) {
	        this.webSocketServiceImpl = webSocketServiceImpl;
	    }

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
				return new ResponseEntity<Sala>(salaParaAtualizar.get(), HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Jogada Não pode ser processada!! ", e);
		}
	}

		@PutMapping("/jogada/comprarcoracoapequeno")
		public ResponseEntity<?> comprarCoracoaPequeno(@RequestBody Sala sala, BindingResult bindingResult) throws JogoInvalidoException {

			if (bindingResult.hasErrors() || sala == null || sala.getHash() == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

			try {
				Optional<Sala> salaParaAtualizar = this.webSocketServiceImpl.compraCoracoesPequenos(sala);
				
	
				if(salaParaAtualizar.isPresent()) {
					return new ResponseEntity<Sala>(salaParaAtualizar.get(), HttpStatus.OK);
				}
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException("Jogada Não pode ser processada!! ", e);
			}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

=======
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
>>>>>>> origin/AlteraçãoBanco-Models
}