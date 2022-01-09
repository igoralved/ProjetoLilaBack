package com.db.jogo.controller;

import java.util.Optional;
import java.util.UUID;

import com.db.jogo.model.Jogador;
import com.db.jogo.service.JogadorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

        if(bindingResult.hasErrors()){
            return new ResponseEntity<Jogador>(jogador, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Jogador>(jogadorService.saveJogador(jogador), HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Jogador> procuraJogador(@PathVariable UUID id) {

        Optional<Jogador> jogador;
        jogador = jogadorService.findById(id);

        if(jogador.isEmpty()) {
            return new ResponseEntity<Jogador>(HttpStatus.NOT_FOUND );
        }
        return new ResponseEntity<Jogador>(jogador.get(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Jogador> atualizar(@RequestBody Jogador jogador) throws IllegalArgumentException {

        try {
            Optional<Jogador> jogadorParaAtualizar = Optional.empty();
            jogadorParaAtualizar = this.jogadorService.findById(jogador.getId());

            if (jogadorParaAtualizar.isPresent()) {

                jogadorParaAtualizar.get().setBonusCoracaoGra(jogador.getBonusCoracaoGra());
                jogadorParaAtualizar.get().setBonusCoracaoPeq(jogador.getBonusCoracaoPeq());
                jogadorParaAtualizar.get().setCoracaoGra(jogador.getCoracaoGra());
                jogadorParaAtualizar.get().setCoracaoPeq(jogador.getCoracaoPeq());
                jogadorParaAtualizar.get().setListaDeCartas(jogador.getListaDeCartas());
                jogadorParaAtualizar.get().setListaDeObjetivos(jogador.getListaDeObjetivos());
                jogadorParaAtualizar.get().setNome(jogador.getNome());
                jogadorParaAtualizar.get().setPontos(jogador.getPontos());

                this.jogadorService.saveJogador(jogadorParaAtualizar.get());

                return new ResponseEntity<>(jogadorParaAtualizar.get(), HttpStatus.OK);
            }

        } catch (Exception e) {
            throw new IllegalArgumentException("Impossível fazer atualização do objeto passado! ", e);
        }

        return new ResponseEntity<Jogador>(HttpStatus.NOT_FOUND);

    }
}