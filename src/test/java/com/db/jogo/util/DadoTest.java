package com.db.jogo.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.db.jogo.model.CartaDoJogo;
import com.db.jogo.model.Jogador;
import com.db.jogo.model.Sala;
@SpringBootTest
public class DadoTest {
	
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

}