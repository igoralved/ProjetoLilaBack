package com.db.jogo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.db.jogo.model.Baralho;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

<<<<<<< HEAD
import java.util.UUID;

=======
>>>>>>> origin/US056-Carregarosdados
@ExtendWith(MockitoExtension.class)
@DisplayName("Baralho Service Teste")
class BaralhoServiceImplTest {

<<<<<<< HEAD
    @Mock
    private BaralhoServiceImpl baralhoServiceImpl;

    Baralho baralho = Baralho.builder().id(UUID.randomUUID()).codigo("LILA1").titulo("Corações de Lila").descricao("Jogo de cartas").build();
=======
	@Mock
    private BaralhoServiceImpl baralhoServiceImpl;

    Baralho baralho = Baralho.builder().codigo("LILA1").titulo("Corações de Lila").descricao("Jogo de cartas").build();
>>>>>>> origin/US056-Carregarosdados

    @DisplayName("Teste do SAVE do Service de um Baralho")
    @Test
    void saveBaralho() {
<<<<<<< HEAD
        when(baralhoServiceImpl.saveBaralho(baralho)).thenReturn(baralho);
        assertEquals(baralho, baralhoServiceImpl.saveBaralho(baralho));
=======
       when(baralhoServiceImpl.saveBaralho(baralho)).thenReturn(baralho);
       assertEquals(baralho, baralhoServiceImpl.saveBaralho(baralho));
>>>>>>> origin/US056-Carregarosdados
    }

}