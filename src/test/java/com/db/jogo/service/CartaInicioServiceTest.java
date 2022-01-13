package com.db.jogo.service;


import com.db.jogo.model.CartaInicio;

import com.db.jogo.repository.CartaInicioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Carta Inicio Service Teste")
class CartaInicioServiceTest {
    @Autowired
    private CartaInicioServiceImpl cartaInicioService;
    @Mock
    private CartaInicioRepository cartaInicioRepository;
    private ArrayList<CartaInicio> cartaInicioArraylist = new ArrayList<>();
    private String id = "d1516d33-ff6f-4dc9-aedf-9316421096cb";
    private CartaInicio cartaInicio;

    @BeforeEach
    void setup() {

        MockitoAnnotations.initMocks(this);
        cartaInicioService = new CartaInicioServiceImpl(cartaInicioRepository);

        cartaInicio = CartaInicio.builder()
                .id(UUID.fromString(id))
                .nome("nome")
                .descricao("descrição")
                .build();
    }


    @DisplayName("Teste do SAVE do Service de uma Carta Do Jogo")
    @Test
    void saveCartaDoJogo() {

        when(cartaInicioService.saveCartaInicio(cartaInicio)).thenReturn(cartaInicio);
        Assertions.assertEquals(cartaInicio, cartaInicioService.saveCartaInicio(cartaInicio));
    }

    @Test
    void findAllCartaInicio() {

        when(cartaInicioService.findAllCartaInicio()).thenReturn(cartaInicioArraylist);
        Assertions.assertEquals(cartaInicioArraylist, cartaInicioService.findAllCartaInicio());
    }

    @Test
    void findByIdCartaInicio() {

        UUID id = UUID.fromString("fd7b6723-77e2-4846-bd22-88df15ca150a");
        when(cartaInicioService.findById(id)).thenReturn(Optional.ofNullable(cartaInicio));

        Assertions.assertEquals(cartaInicioService.findById(id).get() , cartaInicio);
    }
}
