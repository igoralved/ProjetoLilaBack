package com.db.jogo.controller;

import com.db.jogo.model.Baralho;
import com.db.jogo.model.CartaInicio;
import com.db.jogo.service.BaralhoServiceImpl;
import com.db.jogo.service.CartaInicioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*@WebAppConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(CartaInicioController.class)
@DisplayName("Carta Inicio Controller Teste")

public class CartaInicioControllerTest {
    @Mock
   private CartaInicioController cartaInicioController;
    private CartaInicioService cartaInicioService;

    CartaInicio cartaInicio = CartaInicio.builder().nome("Normal").descricao("É uma carta normal").build();

    @DisplayName("Teste do SAVE do Service de uma Carta Ínicio")
    @Test
    void saveCartaInicio() {
        when(cartaInicioController.saveCartaInicio(cartaInicio)).thenReturn(cartaInicio);
        assertEquals(cartaInicio, cartaInicioService.saveCartaInicio(cartaInicio));*/
       // when(cartaInicioService.saveCartaInicio(cartaInicio)).thenReturn(cartaInicio);
      //  assertEquals(cartaInicio,cartaInicioController.saveCartaInicio(cartaInicio));
   // }
//}


@WebAppConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(CartaInicioController.class)
@DisplayName("Carta Incio Controller Teste")
class CartaInicioControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CartaInicioService cartaInicioService;
    CartaInicioController cartaInicioController;

    @Test
    @DisplayName("Teste do POST do Controller da Carta Inicio")
    public void testCriacaoCartaInicio() throws Exception {
        CartaInicio newCartaInicio = CartaInicio.builder()
                .descricao("Descrição")
                .nome("testeNome")
                .descricao("testedescrição")

                .build();

        ObjectMapper mapper = new ObjectMapper();

        String newcartaInicioAsJSON = mapper.writeValueAsString(newCartaInicio);
        this.mockMvc.perform(post("/cartaInicio").content(newcartaInicioAsJSON).accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated());
    }


}
