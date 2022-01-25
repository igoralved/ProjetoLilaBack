package com.db.jogo.service;

import static com.db.jogo.model.Sala.StatusEnum.FINALIZADO;
import static com.db.jogo.model.Sala.StatusEnum.JOGANDO;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import com.db.jogo.dto.SalaResponse;
import com.db.jogo.exception.JogoInvalidoException;
import com.db.jogo.exception.JsonInvalidoException;
import com.db.jogo.model.Baralho;
import com.db.jogo.model.Jogador;
import com.db.jogo.model.Sala;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WebSocketServiceImpl implements WebSocketService {

    private SimpMessagingTemplate template;
    private SalaService salaService;
    private BaralhoService baralhoService;
    private JogadorService jogadorService;

    @Autowired
    private WebSocketServiceImpl(
            SalaService salaService,
            BaralhoService baralhoService,
            JogadorService jogadorService,
            SimpMessagingTemplate template) {
        this.salaService = salaService;
        this.baralhoService = baralhoService;
        this.jogadorService = jogadorService;
        this.template = template;
    }

    public SalaResponse criarJogo(Jogador jogador) throws JogoInvalidoException {
        if (jogador.getNome().isEmpty()) {
            throw new JogoInvalidoException("dados incorretos");
        }
        Sala sala = new Sala();
        SalaResponse salaResp = new SalaResponse();
        Jogador savedJogador = criarJogador(jogador); // cria o jogador
        savedJogador.setIsHost(true); // seta ele como host
        savedJogador = jogadorService.saveJogador(savedJogador); // salva o jogador no banco
        Baralho baralho = baralhoService.findByCodigo("Clila").get();
        sala.setId(UUID.randomUUID());
        sala.setDado(0);
        sala.setBaralho(baralho);
        sala.setJogadores(new ArrayList<>());
        sala.adicionarJogador(savedJogador);
        sala.setHash(sala.generateHash());
        sala.setStatus(Sala.StatusEnum.AGUARDANDO);
        salaResp.setJogador(savedJogador);
        salaResp.setSala(salaService.saveSala(sala));
        return salaResp;
    }

    public Jogador criarJogador(Jogador jogador) {
        jogador.setBonusCoracaoPeq(0);
        jogador.setBonusCoracaoGra(0);
        jogador.setCoracaoPeq(2);
        jogador.setCoracaoGra(0);
        jogador.setPontos(0);
        jogador.setNome(jogador.getNome());
        return jogador;
    }

    public SalaResponse conectarJogo(Jogador jogador, String hash) throws JogoInvalidoException {
        if (jogador == null || hash == null) {
            throw new JogoInvalidoException("Parametros nulos");
        }
        Optional<Sala> sala = salaService.findSalaByHash(hash);

        SalaResponse salaResp = new SalaResponse();

        if (sala.isPresent()) {
            if (sala.get().getStatus() == FINALIZADO) {
                throw new JogoInvalidoException("Jogo ja foi finalizado");
            }
            Jogador savedJogador = criarJogador(jogador); // cria o jogador
            savedJogador.setIsHost(false); // seta ele como NÃO HOST
            savedJogador = jogadorService.saveJogador(savedJogador); // salva o jogador no banco
            sala.get().adicionarJogador(savedJogador);
            salaResp.setJogador(savedJogador);
            salaResp.setSala(sala.get());
            salaService.saveSala(sala.get());
        }
        return salaResp;
    }

    public Integer getQuantidadeJogadores(String hash) {

        Integer numero = salaService.totalJogadores(hash);
        String url = "/gameplay/" + hash;
        template.convertAndSend(url, numero);

        return numero;
    }

    public void sendSala(Sala sala) throws JsonInvalidoException{
        ObjectMapper mapper = new ObjectMapper();
        String salaAsJSON;
        String url = "/gameplay/game-update/" + sala.getHash();
        Sala salaParaEscrever = salaService.updateSala(sala).get();
        try{
            salaAsJSON = mapper.writeValueAsString(salaParaEscrever);
        } catch (JsonProcessingException e){
            throw new JsonInvalidoException("Não foi possível construir o JSON da sala.");
        }
        
        template.convertAndSend(url,salaAsJSON);
        System.err.println("ENVIADO PARA "+url+"\n"+salaAsJSON);
    }
}