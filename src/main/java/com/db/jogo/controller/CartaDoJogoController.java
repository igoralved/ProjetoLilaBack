package com.db.jogo.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.jogo.model.CartaDoJogo;
import com.db.jogo.service.CartaDoJogoService;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/cartadojogo")
public class CartaDoJogoController {

	private CartaDoJogoService cartaDoJogoService;

	@Autowired
	public CartaDoJogoController(CartaDoJogoService cartaInicioService) {
		this.cartaDoJogoService = cartaInicioService;
	}

	@PostMapping
	public ResponseEntity<CartaDoJogo> salvarCartaDoJogo(@RequestBody CartaDoJogo cartaDoJogo,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(cartaDoJogo, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(cartaDoJogoService.saveCartaDoJogo(cartaDoJogo), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<?> listarTodasCartaDoJogo() {

		List<CartaDoJogo> listaCartaDoJogo = cartaDoJogoService.findAll();
		if (!listaCartaDoJogo.isEmpty()) {
			return new ResponseEntity<>(listaCartaDoJogo, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CartaDoJogo> buscarPorIdCartaDoJogo(@PathVariable UUID id) {
		Optional<CartaDoJogo> cartaInicio = Optional.empty();
		cartaInicio = cartaDoJogoService.findById(id);
		if (cartaInicio.isEmpty()) {
			return new ResponseEntity<CartaDoJogo>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CartaDoJogo>(cartaInicio.get(), HttpStatus.OK);

	}

}