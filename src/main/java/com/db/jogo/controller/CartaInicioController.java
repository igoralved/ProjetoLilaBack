package com.db.jogo.controller;


import com.db.jogo.model.Baralho;
import com.db.jogo.model.CartaInicio;
import com.db.jogo.repository.CartaInicioRepository;
import com.db.jogo.service.CartaInicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(exposedHeaders = "erros, content-type")
@RequestMapping("/cartaInicio")
public class CartaInicioController {

    private final CartaInicioService cartaInicoService;
    private ArrayList<CartaInicio> cartaDoJogoArraylist = new ArrayList<>();

    @Autowired
    public CartaInicioController(CartaInicioService cartaInicioService){
        this.cartaInicoService = cartaInicioService;
    }

    @PostMapping
    public ResponseEntity<CartaInicio> saveCartaInicio(@RequestBody CartaInicio cartaInicio, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(cartaInicio, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(cartaInicoService.saveCartaInicio(cartaInicio), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> findAllCartaInicio() {

       List<CartaInicio> listaCartaDoJogo = cartaInicoService.findAllCartaInicio();
        if(!listaCartaDoJogo.isEmpty()){
            return new ResponseEntity<>(listaCartaDoJogo, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(cartaInicoService.findAllCartaInicio(), HttpStatus.NOT_FOUND);
   }

    @GetMapping("/{id}")
    public ResponseEntity<CartaInicio> findByIdcartaInicio(@PathVariable UUID id) {
      Optional<CartaInicio> cartaInicio = Optional.empty();
      cartaInicio = cartaInicoService.findById(id);
      if (cartaInicio.isEmpty()){
          return new ResponseEntity<CartaInicio>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<CartaInicio>(cartaInicio.get(), HttpStatus.OK);


    }


}
