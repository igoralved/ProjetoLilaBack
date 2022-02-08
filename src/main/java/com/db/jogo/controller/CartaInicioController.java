package com.db.jogo.controller;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

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

import com.db.jogo.model.CartaInicio;
import com.db.jogo.service.CartaInicioService;


@RestController
@CrossOrigin(exposedHeaders = "erros, content-type")
@RequestMapping("/cartainicio")
public class CartaInicioController {
private CartaInicioService cartaService;
	
	@Autowired
	public CartaInicioController (CartaInicioService cartaService) {
		this.cartaService=cartaService;
	}

	 @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<CartaInicio> saveCartaInicio(@RequestBody @Validated CartaInicio cartaInicio ,BindingResult bindingResult){
    	if(bindingResult.hasErrors()) {
    		return new ResponseEntity<CartaInicio>(cartaInicio, HttpStatus.BAD_REQUEST);
    	}
    	return new ResponseEntity<CartaInicio>(cartaService.saveCartaInicio(cartaInicio),HttpStatus.CREATED);
    }
    
 
    @GetMapping("/{id}")
    public ResponseEntity<CartaInicio> procuraCarta(@PathVariable UUID id){
    	
    	Optional<CartaInicio> cartaInicio;
    	cartaInicio=cartaService.findById(id);
    	
    	if (cartaInicio.isEmpty()) {
    		return new ResponseEntity<CartaInicio> (HttpStatus.NOT_FOUND);
    				
		}
    	return new ResponseEntity<CartaInicio>(cartaInicio.get(),HttpStatus.OK);
    	
    }
    
    @GetMapping("/listar")
    public ResponseEntity<ArrayList<CartaInicio>> procuraListaCarta(){
    	
    	return new ResponseEntity<ArrayList<CartaInicio>>(cartaService.findAllCartaInicio(),HttpStatus.OK);
    	
    }
    
    	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<CartaInicio>> findCarta() {
		return new ResponseEntity<Iterable<CartaInicio>>(cartaService.findAll(), HttpStatus.OK);
	}
    
}
