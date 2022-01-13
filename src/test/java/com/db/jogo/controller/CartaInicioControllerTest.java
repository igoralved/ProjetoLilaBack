package com.db.jogo.controller;


import com.db.jogo.model.CartaInicio;

import com.db.jogo.service.CartaInicioServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.postgresql.hostchooser.HostRequirement.any;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Carta Inicio Controller Teste")
public class CartaInicioControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CartaInicioServiceImpl cartaInicioService;
   
    String id = "d1516d33-ff6f-4dc9-aedf-9316421096cb";
    CartaInicio newCartaInicio = CartaInicio.builder()
            .id(UUID.fromString(id))
            .nome("nome")
            .descricao("descrição")
            .build();




    @Test
    @DisplayName("Teste do POST/Sucesso do Controller Carta Inicio")
    public void deveRetornarSucesso_QuandoCriarCartaInicio() throws Exception {

        when(cartaInicioService.saveCartaInicio(any(CartaInicio.class))).thenReturn(newCartaInicio);


        ObjectMapper mapper = new ObjectMapper();
        String cartaDoJogoJSON = mapper.writeValueAsString(newCartaInicio);

        mockMvc.perform(MockMvcRequestBuilders.post("/cartainicio").content(cartaDoJogoJSON)
                .accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated()).andExpect(MockMvcResultMatchers.content().json(cartaDoJogoJSON));
    }

    @Test
    @DisplayName("Teste do POST/Error do Controller da Carta Inicio")
    public void deveRetornarErro_QuandoCriarJogadorInvalido() throws Exception {


        when(cartaInicioService.saveCartaInicio(any(CartaInicio.class))).thenReturn(newCartaInicio);
        mockMvc.perform(MockMvcRequestBuilders.post("/cartainicio").accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isBadRequest());

    }
    @Test
    @DisplayName("Teste do GET do Controller Carta Inicio")
    public void deveRetornarSucesso_QuandoBuscar() throws Exception {

        given(cartaInicioService.findById(newCartaInicio.getId())).willReturn(Optional.of(newCartaInicio));

        ObjectMapper mapper = new ObjectMapper();
        String cartaInicioJSON = mapper.writeValueAsString(newCartaInicio);

        mockMvc.perform(get("/cartainicio/" + newCartaInicio.getId()).content(cartaInicioJSON)
                .accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.content().json(cartaInicioJSON));

    }

}
