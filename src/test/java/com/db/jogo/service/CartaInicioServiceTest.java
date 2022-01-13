package com.db.jogo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.db.jogo.model.CartaInicio;

import java.util.ArrayList;
import java.util.UUID;


@ExtendWith(MockitoExtension.class)
@DisplayName("Carta Inicio Service Teste")
public class CartaInicioServiceTest {

    @Mock
    private CartaInicioService cartaInicioService;

    private String id = "d1516d33-ff6f-4dc9-aedf-9316421096cb";
    CartaInicio cartaInicio = CartaInicio.builder().id(UUID.fromString(id))
            .nome("Normal")
            .descricao("É uma carta normal")
            .build();
    private ArrayList<CartaInicio> cartaInicioArrayList = new ArrayList<>();


    @DisplayName("Teste do SAVE do Service de uma Carta Do Jogo")
    @Test
    void saveCartaDoJogo() {
        when(cartaInicioService.saveCartaInicio(cartaInicio)).thenReturn(cartaInicio);
        assertEquals(cartaInicio, cartaInicioService.saveCartaInicio(cartaInicio));

    }

    @Test
    void findAllCartaDoJogo() {
        when(cartaInicioService.findAllCartaInicio()).thenReturn(cartaInicioArrayList);
        assertEquals(cartaInicioArrayList, cartaInicioService.findAllCartaInicio());

    }

    @Test
    void findByIdCartaDoJogo() {
        when(cartaInicioService.findById(UUID.fromString(id))).thenReturn(cartaInicioService.findById(UUID.fromString(id)));
        assertEquals(cartaInicioService.findById(UUID.fromString(id)), cartaInicioService.findById(UUID.fromString(id)));
    }
}

