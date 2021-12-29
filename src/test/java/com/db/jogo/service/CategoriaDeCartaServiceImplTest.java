package com.db.jogo.service;

import com.db.jogo.model.CategoriaDeCarta;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("CategoriaDeCarta Service Teste")
public class CategoriaDeCartaServiceImplTest {

    @Mock
    private CategoriaDeCartaServicelmpl categoriaDeCartaServicelmpl;

    CategoriaDeCarta categoriaDeCarta = CategoriaDeCarta.builder().nome("Fisica").build();


    @DisplayName("Teste do SAVE do Service da CategoriaDeCarta")
    @Test
    void saveCategoria() {
        when(categoriaDeCartaServicelmpl.saveCategoria(categoriaDeCarta)).thenReturn(categoriaDeCarta);
        assertEquals(categoriaDeCarta, categoriaDeCartaServicelmpl.saveCategoria(categoriaDeCarta));
    }

}
