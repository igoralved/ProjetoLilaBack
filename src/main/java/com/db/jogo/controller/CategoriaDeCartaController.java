package com.db.jogo.controller;

import com.db.jogo.model.CategoriaDeCarta;
import com.db.jogo.service.CategoriaDeCartaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/categoriaDeCarta")
public class CategoriaDeCartaController {

    @Autowired
    private CategoriaDeCartaService categoriaDeCartaService;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<CategoriaDeCarta> saveCategoria(@RequestBody CategoriaDeCarta categoria, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || categoria.getNome() == null ) {
            return new ResponseEntity<CategoriaDeCarta>(categoria, HttpStatus.BAD_REQUEST);
        }
            return new ResponseEntity<CategoriaDeCarta>(categoriaDeCartaService.saveCategoria(categoria), HttpStatus.CREATED);
        }

        @RequestMapping(method = RequestMethod.GET, produces = "application/json")
        public ResponseEntity<Iterable<CategoriaDeCarta>> findCategoria () {
            return new ResponseEntity<Iterable<CategoriaDeCarta>>(categoriaDeCartaService.findAll(), HttpStatus.OK);
        }
    }

