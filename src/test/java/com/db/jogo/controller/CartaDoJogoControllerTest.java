package com.db.jogo.controller;

import com.db.jogo.model.CartaDoJogo;
import com.db.jogo.service.CartaDoJogoService;
import com.db.jogo.service.CartaDoJogoServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebAppConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(CartaDoJogoController.class)
@DisplayName("Carta Do Jogo Controller Teste")
public class CartaDoJogoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CartaDoJogoService cartaDoJogoService;
    String id = "d1516d33-ff6f-4dc9-aedf-9316421096cb";
    CartaDoJogo newCartaDoJogo = CartaDoJogo.builder().id(UUID.fromString(id)).bonus(true)
            .valorCorGrande(1)
            .valorCorPequeno(0)
            .categoria("Fisica")
            .fonte("Google")
            .tipo("Informação")
            .pontos(3)
            .texto("Teste")
            .build();




    @Test
    @DisplayName("Teste do POST do Controller da Carta Do Jogo")
    public void testCriacaoCartaDoJogo() throws Exception {
        CartaDoJogoServiceImpl cataDoJogoService;


        CartaDoJogo newCartaDoJogo = CartaDoJogo.builder().id(UUID.randomUUID()).bonus(true)
                .valorCorGrande(1)
                .valorCorPequeno(0)
                .categoria("Fisica")
                .fonte("Google")
                .tipo("Informação")
                .pontos(3)
                .texto("Teste")
                .build();
        // UUID id = "d1516d33-ff6f-4dc9-aedf-9316421096cb";

        ObjectMapper mapper = new ObjectMapper();

        String newCartaDoJogoAsJSON = mapper.writeValueAsString(newCartaDoJogo);
        this.mockMvc.perform(post("/cartadojogo").content(newCartaDoJogoAsJSON).accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated());

    }
    @Test
    @DisplayName("Teste do GET do Controller do Jogador")
    public void deveRetornarSucesso_QuandoBuscar() throws Exception {

        given(cartaDoJogoService.findById(newCartaDoJogo.getId())).willReturn(Optional.of(newCartaDoJogo));

        ObjectMapper mapper = new ObjectMapper();
        String jogadorComoJSON = mapper.writeValueAsString(newCartaDoJogo);

        mockMvc.perform(get("/cartadojogo/" + cartaDoJogoService.findAll()).content(jogadorComoJSON)
                .accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andExpect((ResultMatcher) content().json(jogadorComoJSON));


    }

}
