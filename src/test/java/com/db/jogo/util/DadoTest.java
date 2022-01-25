package com.db.jogo.util;

import com.db.jogo.model.Jogador;
import com.db.jogo.model.Sala;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DadoTest {

    @Mock
    private Dado dado;

    @Test
    public void verificaResultadoDoDado() throws Exception {
        Jogador jogador = new Jogador();
        Jogador dado = Dado.resultadoDoDado("Ação", 3, jogador);
         assertEquals(dado, Dado.resultadoDoDado("Ação",3,jogador));

    }

}
