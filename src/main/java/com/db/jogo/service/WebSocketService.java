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

    @Autowired
    private WebSocketService ( SalaService salaService, BaralhoService baralhoService){
        this.salaService = salaService;
        this.baralhoService = baralhoService;
    }


//    public WebSocketService(SimpMessagingTemplate template) {
//        this.template = template;
//    }
//
//    @Async
//    public void jogada(Sala sala) {
//        String URLresposta = "/" + sala.getHash().toString();
//
//        try {
//
//            template.convertAndSend(URLresposta, gerarMensagem()
//                    + "\n: Sala Número: " + sala.getId()
//                    + " \n : " + sala.getBonusCoracaoGrande()
//                    + " \n : " + sala.getBonusCoracaoPequeno()
//                    + " \n : " + sala.getCoracaoGrande()
//                    + " \n : " + sala.getCoracaoPequeno()
//
//            );
//
//        } catch (Exception e) {
//            log.error("Erro durante o procesamento.", e);
//        }
//    }
//
//    private String gerarMensagem() {
//        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
//    }

    public Sala criarJogo ( Jogador jogador){
        Sala sala = new Sala();
        Baralho baralho = baralhoService.findById("lila1");
        sala.setId(UUID.randomUUID());
        sala.setBaralho(baralho);
        sala.setJogadores(new ArrayList<>());
        sala.adicionarJogador(jogador);
        sala.setHash(sala.generateHash());
        return sala;
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
