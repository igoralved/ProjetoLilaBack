package com.db.jogo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.db.jogo.model.Sala;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WebSocketService {

    private SimpMessagingTemplate template;

    @Autowired
    public WebSocketService(SimpMessagingTemplate template) {
        this.template = template;
    }

    @Async
    public void jogada(Sala sala) {
        String URLresposta = "/" + sala.getHash().toString();

        try {

            template.convertAndSend(URLresposta, gerarMensagem() 
                    + "\n: Sala Número: " + sala.getId()
                    + " \n : " + sala.getBonusCoracaoGrande()
                    + " \n : " + sala.getBonusCoracaoPequeno()
                    + " \n : " + sala.getCoracaoGrande()
                    + " \n : " + sala.getCoracaoPequeno()

            );

        } catch (Exception e) {
            log.error("Erro durante o procesamento.", e);
        }
    }

    private String gerarMensagem() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
}
