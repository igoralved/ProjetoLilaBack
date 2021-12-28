package com.db.jogo.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.db.jogo.model.Admin;
import com.db.jogo.model.Baralho;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Baralho Service Teste")
class BaralhoServiceImplTest {

	@Mock
    private BaralhoServiceImpl baralhoServiceImpl;

    Baralho baralho = Baralho.builder().codigo("LILA1").titulo("Corações de Lila").descricao("Jogo de cartas").build();

    @DisplayName("Teste do SAVE do Service de um Baralho")
    @Test
    void saveBaralho() {
       when(baralhoServiceImpl.saveBaralho(baralho)).thenReturn(baralho);
       assertEquals(baralho, baralhoServiceImpl.saveBaralho(baralho));
    }

}