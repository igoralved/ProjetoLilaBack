package com.db.jogo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.db.jogo.model.CartaObjetivo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@DisplayName("CartaObjetivo Service Teste")
class CartaObjetivoServiceImplTest {

    @Mock
    private CartaObjetivoServiceImpl cartaObjetivoServiceImpl;

    CartaObjetivo cartaObjetivo = CartaObjetivo.builder().id(UUID.randomUUID()).categoria("Filme").classificacao("Deficiencia fisica").descricao("Lorem ipsum").pontos(3).build();

    @DisplayName("Teste do SAVE do Service de todas as cartas de objetivo")
    @Test
    void saveCartaObjetivo() {
        when(cartaObjetivoServiceImpl.saveCartaObjetivo(cartaObjetivo)).thenReturn(cartaObjetivo);
        assertEquals(cartaObjetivo, cartaObjetivoServiceImpl.saveCartaObjetivo(cartaObjetivo));
    }

}