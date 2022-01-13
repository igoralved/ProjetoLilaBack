package com.db.jogo.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;
import java.util.UUID;

@WebAppConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(CartaObjetivoController.class)
@DisplayName("Carta Objetivo Controller Teste")
class CartaObjetivoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CartaObjetivoServiceImpl cartaObjetivoService;
    String id = "d1516d33-ff6f-4dc9-aedf-9316421096cb";
    CartaObjetivo newCartaObjetivo = CartaObjetivo.builder().id(UUID.fromString(id))
            .classificacao("Deficiencia Física")
            .categoria("Filmes")
            .pontos(3)
            .descricao("Exemplo descrição")
            .build();

    @Test
    @DisplayName("Teste do POST/Error do Controller da Carta Objetivo")
    public void deveRetornarErro_QuandoCriarCartaObjetivoInvalido() throws Exception {

        when(cartaObjetivoService.saveCartaObjetivo(any(CartaObjetivo.class))).thenReturn(newCartaObjetivo);

        mockMvc.perform(MockMvcRequestBuilders.post("/cartaobjetivo").accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Teste do POST/Sucesso do Controller Carta Objetivo")
    public void deveRetornarSucesso_QuandoCriarCartaObjetivo() throws Exception {

        when(cartaObjetivoService.saveCartaObjetivo(any(CartaObjetivo.class))).thenReturn(newCartaObjetivo);

        ObjectMapper mapper = new ObjectMapper();
        String cartaObjetivoJSON = mapper.writeValueAsString(newCartaObjetivo);

        mockMvc.perform(MockMvcRequestBuilders.post("/cartaobjetivo").content(cartaObjetivoJSON)
                .accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated()).andExpect(MockMvcResultMatchers.content().json(cartaObjetivoJSON));
    }

    @Test
    @DisplayName("Teste do GET do Controller Carta Objetivo")
    public void deveRetornarSucesso_QuandoBuscarCartaObjetivo() throws Exception {

        given(cartaObjetivoService.findById(newCartaObjetivo.getId())).willReturn(Optional.of(newCartaObjetivo));

        ObjectMapper mapper = new ObjectMapper();
        String cartaObjetivoJSON = mapper.writeValueAsString(newCartaObjetivo);

        mockMvc.perform(get("/cartaobjetivo/" + newCartaObjetivo.getId()).content(cartaObjetivoJSON)
                .accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.content().json(cartaObjetivoJSON));

    }
}
