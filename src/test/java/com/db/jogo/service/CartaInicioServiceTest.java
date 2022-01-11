package com.db.jogo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.db.jogo.model.CartaInicio;


@ExtendWith(MockitoExtension.class)
@DisplayName("Carta Inicio Service Teste")
public class CartaInicioServiceTest {

    @Mock
    private CartaInicioService cartaInicioService;

    CartaInicio cartaInicio = CartaInicio.builder().nome("Normal").descricao("É uma carta normal").build();

    @DisplayName("Teste do SAVE do Service de uma Carta Ínicio")
    @Test
    void saveBaralho() {
        when(cartaInicioService.saveCartaInicio(cartaInicio)).thenReturn(cartaInicio);
        assertEquals(cartaInicio, cartaInicioService.saveCartaInicio(cartaInicio));
    }

  /*  @Test
    void findAllCartaInicio() {
    }

    @Test
    void findByIdCartaInicio() {
    }*/

}