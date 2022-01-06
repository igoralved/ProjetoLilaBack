package com.db.jogo.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.db.jogo.model.CartaObjetivo;
import com.db.jogo.service.CartaObjetivoServiceImpl;
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

import java.util.UUID;

@WebAppConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(CartaObjetivoController.class)
@DisplayName("Carta Objetivo Controller Teste")
class CartaObjetivoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CartaObjetivoServiceImpl cartaObjetivoServiceImpl;

    @Test
    @DisplayName("Teste do POST do Controller do Baralho")
    public void testCriacaoCartaObjetivo() throws Exception {

        CartaObjetivo newCartaObjetivo = CartaObjetivo.builder().id(UUID.randomUUID())
                .classificacao("Deficiencia Visual")
                .categoria("Filmes")
                .pontos(2)
                .descricao("Exemplo descrição")
                .build();

        ObjectMapper mapper = new ObjectMapper();

        String newCartaObjetivoAsJSON = mapper.writeValueAsString(newCartaObjetivo);
        this.mockMvc.perform(post("/CartaObjetivo").content(newCartaObjetivoAsJSON).accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated());
    }
}
