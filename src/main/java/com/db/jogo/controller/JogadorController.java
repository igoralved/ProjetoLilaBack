package com.db.jogo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.db.jogo.model.Jogador;
import com.db.jogo.service.JogadorService;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/jogador")
public class JogadorController {

	private JogadorService jogadorService;
	
	@Autowired
	public JogadorController(JogadorService jogadorService) {
		this.jogadorService = jogadorService;
	}
	
	
	 @RequestMapping(method = RequestMethod.POST, produces = "application/json")
	    public ResponseEntity<Jogador> saveJogador(@RequestBody Jogador jogador, BindingResult bindingResult) {
	        if(bindingResult.hasErrors()){
	            return new ResponseEntity<Jogador>(jogador, HttpStatus.BAD_REQUEST);
	        }
	        return new ResponseEntity<Jogador>(jogadorService.saveJogador(jogador), HttpStatus.CREATED);
	    }
}