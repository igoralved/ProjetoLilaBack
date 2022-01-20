package com.db.jogo.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.db.jogo.model.CartaDoJogo;
import com.db.jogo.model.Jogador;

class RegrasDoJogoTest {

	@Test
	@DisplayName("Teste valida coracoes VERDADEIRO")
	void testValidaCoracaoVerdadeiro() {
		
		Jogador jogador = Jogador.builder()
				.id(UUID.randomUUID())
				.nome("joão")
				.bonusCoracaoGra(1)
				.bonusCoracaoPeq(1)
				.coracaoGra(1)
				.pontos(1)
				.coracaoPeq(2)
				.build();
		
		CartaDoJogo carta = CartaDoJogo.builder()
				.valorCorGrande(1)
				.valorCorPequeno(2)
				.build();
		
			boolean valida =  
				RegrasDoJogo.validaCompraCarta(jogador, carta);
		assertEquals(valida, true);
	}

	@Test
	@DisplayName("Teste valida coracoes FALSO")
	void testValidaCoracaoFalso() {
		
		Jogador jogador = Jogador.builder()
				.id(UUID.randomUUID())
				.nome("joão")
				.bonusCoracaoGra(0)
				.bonusCoracaoPeq(0)
				.coracaoGra(1)
				.pontos(1)
				.coracaoPeq(1)
				.build();
		
		CartaDoJogo carta = CartaDoJogo.builder()
				.valorCorGrande(1)
				.valorCorPequeno(2)
				.build();
		
			boolean valida =  
				RegrasDoJogo.validaCompraCarta(jogador, carta);
		assertEquals(valida, false);
	}

}
