package com.db.jogo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.db.jogo.model.CategoriaDeCarta;
import com.db.jogo.service.CategoriaDeCartaServicelmpl;
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

@WebAppConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(CategoriaDeCartaController.class)
@DisplayName("CategoriaDeCarta Controller Teste")
public class CategoriaDeCartaControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CategoriaDeCartaServicelmpl categoriaService;

    @Test
    @DisplayName("teste do Post do ontroller da Categoria")
    public void testCriacaoCategoria() throws Exception {
        CategoriaDeCarta newCategoria = CategoriaDeCarta.builder().nome("Fisica")
                .build();
        ObjectMapper mapper = new ObjectMapper();

        String newCategoriaAsJSON = mapper.writeValueAsString(newCategoria);
        this.mockMvc.perform(post("/categoriaDeCarta").content(newCategoriaAsJSON).accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated());
    }

}
