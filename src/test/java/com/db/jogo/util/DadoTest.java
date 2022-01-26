package com.db.jogo.util;

import com.db.jogo.model.Jogador;
import com.db.jogo.model.Sala;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.db.jogo.model.CartaDoJogo;

@SpringBootTest
class DadoTest {

    @MockBean
    private Dado dado;

    @Test
    public void verificaResultadoDoDado() throws Exception {

        Jogador jogador = new Jogador();
        jogador.setBonusCoracaoPeq(0);
        jogador.setBonusCoracaoGra(0);
        jogador.setCoracaoPeq(2);
        jogador.setCoracaoGra(0);


        CartaDoJogo	carta = CartaDoJogo.builder()
                .bonus(true)
                .categoria("Ação")
                .fonte("")
                .pontos(0)
                .valorCorGrande(0)
                .valorCorPequeno(0)
                .tipo("Ação")
                .build();


        Sala sala = Sala.builder()
                .hash("IZHW")
                .dado(0)
                .build();


        when(dado.girarDado(carta, jogador, sala ))
                .thenReturn(jogador);
        assertThat(dado.girarDado(carta, jogador, sala)).isNotNull();
        assertEquals(jogador, dado.girarDado(carta, jogador, sala));
    }

    @Test
    public void  validaQuantidadeCoracoes() throws Exception{


        Jogador jogador = new Jogador();
        jogador.setBonusCoracaoGra(1);
        jogador.setBonusCoracaoPeq(1);
        jogador.setCoracaoGra(1);
        jogador.setCoracaoPeq(1);

        Integer resultado =Dado.validaQuantidadeCoracoes(jogador);
        assertEquals(4,resultado);
    }
}


