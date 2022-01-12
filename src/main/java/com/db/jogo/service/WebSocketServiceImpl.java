package com.db.jogo.service;

import com.db.jogo.exception.JogoInvalidoException;
import com.db.jogo.model.Baralho;
import com.db.jogo.model.Jogador;
import com.db.jogo.model.Sala;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static com.db.jogo.model.Sala.StatusEnum.FINALIZADO;
import static com.db.jogo.model.Sala.StatusEnum.JOGANDO;

@Slf4j
@Service
public class WebSocketServiceImpl implements WebSocketService{

    private SimpMessagingTemplate template;
    private SalaService salaService;
    private BaralhoService baralhoService;
    private JogadorService jogadorService;

    @Autowired
    private WebSocketServiceImpl(
        SalaService salaService,
        BaralhoService baralhoService,
        JogadorService jogadorService,
        SimpMessagingTemplate template)
    {
        this.salaService = salaService;
        this.baralhoService = baralhoService;
        this.jogadorService = jogadorService;
        this.template = template;
    }

    public Sala criarJogo (Jogador jogador) throws JogoInvalidoException{
        if(jogador.getNome().isEmpty()){
            throw new JogoInvalidoException("dados incorretos");
        }
            Sala sala = new Sala();
            Jogador savedJogador = jogadorService.saveJogador(criaJogador(jogador));
            Baralho baralho = baralhoService.findByCodigo("Clila").get();
            sala.setId(UUID.randomUUID());
            sala.setBaralho(baralho);
            sala.setJogadores(new ArrayList<>());
            sala.adicionarJogador(savedJogador);
            sala.setHash(sala.generateHash());
            return salaService.saveSala(sala);

    }

    public Jogador criaJogador(Jogador jogador) {
            jogador.setBonusCoracaoPeq(0);
            jogador.setBonusCoracaoGra(0);
            jogador.setCoracaoPeq(2);
            jogador.setCoracaoGra(2);
            jogador.setPontos(0);
            jogador.setNome(jogador.getNome());
        return jogador;
    }

    public Optional<Sala> conectarJogo(Jogador jogador, String hash) throws JogoInvalidoException {
            if (jogador == null || hash == null) {
                throw new JogoInvalidoException("Parametros nulos");
            }
            Optional<Sala> sala = salaService.findSalaByHash(hash);
             if(sala.isPresent()){
                if (sala.get().getStatusEnum() == FINALIZADO) {
                throw new JogoInvalidoException("Jogo ja foi finalizado");
                }
                sala.get().adicionarJogador(jogadorService.saveJogador(jogador));
                sala.get().setStatusEnum(JOGANDO);
            }
            return sala;
    }


}