package com.db.jogo.service;


import com.db.jogo.model.CartaDoJogo;
import com.db.jogo.repository.CartaDoJogoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
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
@DisplayName("Carta Do Jogo Service Teste")
class CartaDoJogoServiceTest {
    @Autowired
    private CartaDoJogoServiceImpl cartaDoJogoService;
    @Mock
    private CartaDoJogoRepository cartaDoJogoRepository;
    private ArrayList<CartaDoJogo> cartaDoJogoArraylist = new ArrayList<>();
    private String id = "d1516d33-ff6f-4dc9-aedf-9316421096cb";
    private CartaDoJogo cartaDoJogo;

    @BeforeEach
    void setup() {

        MockitoAnnotations.initMocks(this);
        cartaDoJogoService = new CartaDoJogoServiceImpl(cartaDoJogoRepository);

        cartaDoJogo = CartaDoJogo.builder()
                .id(UUID.fromString(id))
                .bonus(true)
                .valorCorGrande(1)
                .valorCorPequeno(0)
                .categoria("Fisica")
                .fonte("Google")
                .tipo("Informação")
                .pontos(3)
                .texto("Teste")
                .build();
    }


    @DisplayName("Teste do SAVE do Service de uma Carta Do Jogo")
    @Test
    void saveCartaDoJogo() {

        when(cartaDoJogoService.saveCartaDoJogo(cartaDoJogo)).thenReturn(cartaDoJogo);
        Assertions.assertEquals(cartaDoJogo, cartaDoJogoService.saveCartaDoJogo(cartaDoJogo));
    }

    @Test
    void findAllCartaDoJogo() {

        when(cartaDoJogoService.findAll()).thenReturn(cartaDoJogoArraylist);
        Assertions.assertEquals(cartaDoJogoArraylist, cartaDoJogoService.findAll());
    }

    @Test
    void findByIdCartaDoJogo() {

        UUID id = UUID.fromString("fd7b6723-77e2-4846-bd22-88df15ca150a");
        when(cartaDoJogoService.findById(id)).thenReturn(Optional.ofNullable(cartaDoJogo));

        Assertions.assertEquals(cartaDoJogoService.findById(id).get() , cartaDoJogo);
    }
}