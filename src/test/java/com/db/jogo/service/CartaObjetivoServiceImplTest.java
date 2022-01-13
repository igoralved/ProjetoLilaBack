package com.db.jogo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.db.jogo.model.CartaObjetivo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@DisplayName("CartaObjetivo Service Teste")
class CartaObjetivoServiceImplTest {

    @Mock
    private CartaObjetivoServiceImpl cartaObjetivoService;

    CartaObjetivo cartaObjetivo = CartaObjetivo.builder().id(UUID.randomUUID())
            .categoria("Filme")
            .classificacao("Deficiencia fisica")
            .descricao("Lorem ipsum")
            .pontos(3)
            .build();

    private final ArrayList<CartaObjetivo> cartaObjetivoArraylist = new ArrayList<>();

    Optional<CartaObjetivo> cartaID;

    @DisplayName("Teste do SAVE do Service de todas as cartas de objetivo")

    @Test
    void saveCartaObjetivo() {
        when(cartaObjetivoService.saveCartaObjetivo(cartaObjetivo)).thenReturn(cartaObjetivo);
        assertEquals(cartaObjetivo, cartaObjetivoService.saveCartaObjetivo(cartaObjetivo));
    }

    @Test
    void findCartaObjetivo() {
        when(cartaObjetivoService.findAll()).thenReturn(cartaObjetivoArraylist);
        assertEquals(cartaObjetivoArraylist, cartaObjetivoService.findAll());

    }

    @Test
    void findCartaObjetivoById() {
        UUID id = UUID.fromString("fd7b6723-77e2-4846-bd22-88df15ca150a");
        when(cartaObjetivoService.findById(id)).thenReturn(Optional.ofNullable(cartaObjetivo));
        Assertions.assertEquals(cartaObjetivoService.findById(id).get() , cartaObjetivo);
    }

}