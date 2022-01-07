package com.db.jogo.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import com.db.jogo.exception.JogoInvalidoException;
import com.db.jogo.model.Baralho;
import com.db.jogo.model.Jogador;
import com.db.jogo.model.Sala;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import static com.db.jogo.model.Sala.StatusEnum.FINALIZADO;
import static com.db.jogo.model.Sala.StatusEnum.JOGANDO;

@Slf4j
@Service
public class WebSocketService {

    private SimpMessagingTemplate template;
    private SalaService salaService;
    private BaralhoService baralhoService;
    private JogadorService jogadorService;

    @Autowired
    private WebSocketService ( SalaService salaService, BaralhoService baralhoService, JogadorService jogadorService){
        this.salaService = salaService;
        this.baralhoService = baralhoService;
        this.jogadorService = jogadorService;
    }

    public Sala criarJogo (Jogador jogador){
        Sala sala = new Sala();
        Jogador savedJogador = jogadorService.saveJogador(jogador);
        Baralho baralho = baralhoService.findById("lila1");
        sala.setId(UUID.randomUUID());
        sala.setBaralho(baralho);
        sala.setJogadores(new ArrayList<>());
        sala.adicionarJogador(savedJogador);
        sala.setHash(sala.generateHash());
        return salaService.saveSala(sala);
    }

    public Optional<Sala> conectarJogo(Jogador jogador, String hash) throws JogoInvalidoException{
        Optional<Sala> sala = salaService.findSalaByHash(hash);
        if(sala.get().getStatusEnum() == FINALIZADO){
            throw new JogoInvalidoException("Jogo ja foi finalizado");
        }
        sala.ifPresent(value -> value.adicionarJogador(jogador));
        sala.get().setStatusEnum(JOGANDO);
        return sala;
    }
}