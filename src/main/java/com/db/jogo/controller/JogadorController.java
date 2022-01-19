package com.db.jogo.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.db.jogo.model.Jogador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.db.jogo.service.JogadorServiceImpl;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/jogador")
public class JogadorController {

	private JogadorServiceImpl jogadorService;

	@Autowired
	public JogadorController(JogadorServiceImpl jogadorService) {
		this.jogadorService = jogadorService;
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Jogador> saveJogador(@RequestBody @Validated Jogador jogador, BindingResult bindingResult) {

		Optional<Jogador> jogadorParaSalvar = Optional.of(jogador);

		if (bindingResult.hasErrors() || jogadorParaSalvar.isEmpty()) {
			return new ResponseEntity<Jogador>(jogador, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Jogador>(jogadorService.saveJogador(jogador), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Jogador> procuraJogador(@PathVariable UUID id) {

		Optional<Jogador> jogador = Optional.empty();
		jogador = jogadorService.findById(id);

		if (jogador.isEmpty()) {
			return new ResponseEntity<Jogador>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Jogador>(jogador.get(), HttpStatus.OK);
	}

	@GetMapping("/todos")
	public Iterable<Jogador> listarTodos(){
		return this.jogadorService.findAll();
	}


	@RequestMapping(method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Jogador> atualizar(@RequestBody Jogador jogador, BindingResult bindingResult) {

		if (bindingResult.hasErrors() || jogador == null || jogador.getId() == null) {

			return new ResponseEntity<Jogador>(HttpStatus.NOT_FOUND);
		}

		Optional<Jogador> jogadorParaAtualizar = this.jogadorService.atualizarJogador(jogador);

		if (jogadorParaAtualizar.isPresent()) {
			return new ResponseEntity<Jogador>(jogador, HttpStatus.OK);
		}
		return new ResponseEntity<Jogador>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/totaljogadores")
	public Integer totalJogadores() {
		return this.jogadorService.totalJogadores();
	}

	@GetMapping("/podeJogar")
	public Boolean podeJogar() {
		Integer numeroJogadores = this.totalJogadores();
		if(numeroJogadores < 2 || numeroJogadores > 6) {
			return false;
		}
		return true;
	}

}
