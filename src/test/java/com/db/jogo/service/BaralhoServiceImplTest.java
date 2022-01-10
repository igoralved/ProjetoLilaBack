package com.db.jogo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.db.jogo.model.Baralho;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@DisplayName("Baralho Service Teste")
class BaralhoServiceImplTest {

    @Mock
    private BaralhoServiceImpl baralhoServiceImpl;

    Baralho baralho = Baralho.builder().id(UUID.randomUUID()).codigo("LILA1").titulo("Corações de Lila").descricao("Jogo de cartas").build();

    @DisplayName("Teste do SAVE do Service de um Baralho")
    @Test
    void saveBaralho() {
        when(baralhoServiceImpl.saveBaralho(baralho)).thenReturn(baralho);
        assertEquals(baralho, baralhoServiceImpl.saveBaralho(baralho));
    }

}