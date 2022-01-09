package com.db.jogo.controller;


import com.db.jogo.model.CartaInicio;
import com.db.jogo.repository.CartaInicioRepository;
import com.db.jogo.service.CartaInicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController@CrossOrigin(exposedHeaders = "erros, content-type")
@RequestMapping("/cartaInicio")
public class CartaInicioController {


    private CartaInicioRepository cartaInicioRepository;
    private ArrayList<CartaInicio> cartaInicioArraylist = new ArrayList<>();
    private CartaInicioService cartaInicioService;

    @Autowired
    public CartaInicioController( CartaInicioRepository cartaInicioRepository, CartaInicioService cartaInicioService){
        this.cartaInicioRepository = cartaInicioRepository;
        this.cartaInicioService = cartaInicioService;

    }

    @PostMapping
    public ResponseEntity<CartaInicio> saveCartaInicio(@RequestBody CartaInicio cartaInicio ){
        return new ResponseEntity<>(cartaInicio, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAllCartaInicio() {
        List<CartaInicio> listaCartaInicio = cartaInicioRepository.findAll();
        if(!listaCartaInicio.isEmpty()){
            return new ResponseEntity<>(listaCartaInicio, HttpStatus.FOUND);
        }
        return new ResponseEntity<>("Não existe Cartas Cadastradas", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdCartaInicio(@PathVariable UUID id){
        Optional<CartaInicio> cartaInicioPorId = cartaInicioRepository.findById(id);
        if(cartaInicioPorId.isPresent()){
            return new ResponseEntity<>(cartaInicioPorId, HttpStatus.FOUND);
        }
        return new ResponseEntity<>("carta não encontrada", HttpStatus.NOT_FOUND);
    }


}
