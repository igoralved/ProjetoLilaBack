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

import com.db.jogo.model.Baralho;
import com.db.jogo.service.BaralhoService;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/baralho")
public class BaralhoController {

	@Autowired
	private BaralhoService baralhoService;

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Baralho> saveBaralho(@RequestBody Baralho baralho, BindingResult bindingResult){
		if(bindingResult.hasErrors() || (baralho.getId() == null) || (baralho.getTitulo() == null) || (baralho.getDescricao() == null)) {
			return new ResponseEntity<Baralho>(baralho, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Baralho>(baralhoService.saveBaralho(baralho), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<Baralho>> findBaralho() {
		return new ResponseEntity<Iterable<Baralho>>(baralhoService.findAll(), HttpStatus.OK);
	}
}

