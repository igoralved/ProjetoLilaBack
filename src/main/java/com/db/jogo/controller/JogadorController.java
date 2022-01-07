package com.db.jogo.controller;

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
    public ResponseEntity<Jogador> saveJogador(@RequestBody @Validated Jogador jogador, BindingResult bindingResult) {

        Optional<Jogador>jogadorParaSalvar = Optional.of(jogador);

        if(bindingResult.hasErrors() || jogadorParaSalvar.isEmpty()){
            return new ResponseEntity<Jogador>(jogador, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Jogador>(jogadorService.saveJogador(jogador), HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Jogador> procuraJogador(@PathVariable UUID id) {

        Optional<Jogador> jogador= Optional.empty();
        jogador = jogadorService.findById(id);

        if(jogador.isEmpty()) {
            return new ResponseEntity<Jogador>(HttpStatus.NOT_FOUND );
        }
        return new ResponseEntity<Jogador>(jogador.get(), HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Jogador> atualizar(@RequestBody Jogador jogador) {

        try {
            Optional<Jogador> jogadorParaAtualizar = Optional.empty();
            jogadorParaAtualizar = this.jogadorService.findById(jogador.getId());

            if (!jogadorParaAtualizar.isEmpty()) {

                jogadorParaAtualizar.get().setBonusCoracaoGra(jogador.getBonusCoracaoGra());
                jogadorParaAtualizar.get().setBonusCoracaoPeq(jogador.getBonusCoracaoPeq());
                jogadorParaAtualizar.get().setCoracaoGra(jogador.getCoracaoGra());
                jogadorParaAtualizar.get().setCoracaoPeq(jogador.getCoracaoPeq());
                jogadorParaAtualizar.get().setListaDeCartas(jogador.getListaDeCartas());
                jogadorParaAtualizar.get().setListaDeObjetivos(jogador.getListaDeObjetivos());
                jogadorParaAtualizar.get().setNome(jogador.getNome());
                jogadorParaAtualizar.get().setPontos(jogador.getPontos());

                this.jogadorService.saveJogador(jogadorParaAtualizar.get());

                return new ResponseEntity<Jogador>(jogadorParaAtualizar.get(), HttpStatus.OK);
            }

        } catch (Exception e) {
            new IllegalArgumentException("Impossível fazer atualização do objeto passado! ", e);
        }

        return new ResponseEntity<Jogador>(HttpStatus.NOT_FOUND);

    }


}