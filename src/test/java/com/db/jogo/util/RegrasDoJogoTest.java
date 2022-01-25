package com.db.jogo.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.db.jogo.model.CartaDoJogo;
import com.db.jogo.model.Jogador;

class RegrasDoJogoTest {
	
	private CartaDoJogo carta;	
	private Jogador jogador;
	
	@BeforeEach 
	void criaCarta () {
		carta = CartaDoJogo.builder()
				.valorCorGrande(1)
				.valorCorPequeno(2)
				.build();
		
		 jogador = Jogador.builder()
				.id(UUID.randomUUID())
				.nome("joão")
				.bonusCoracaoGra(1)
				.bonusCoracaoPeq(1)
				.coracaoGra(1)
				.pontos(1)
				.coracaoPeq(2)
				.build();
	}
	@Test
	@DisplayName("Teste valida compra CartaDoJogo VERDADEIRO")
	void testValidaCoracoesVerdadeiro() {
		
			boolean valida =  
				RegrasDoJogo.validaCompraCarta(jogador, this.carta);
		assertEquals(valida, true);
	}

	@Test
	@DisplayName("Teste valida compra CartaDoJogo FALSO")
	void testValidaCoracoesFalso() {
		jogador.setBonusCoracaoGra(0);
		jogador.setBonusCoracaoPeq(0);
		jogador.setCoracaoPeq(1);	
			
			boolean valida =  
				RegrasDoJogo.validaCompraCarta(jogador, carta);
		assertEquals(valida, false);
	}
	
	@Test
	@DisplayName("Teste valida compra CartaDoJogo FALSO")
	void testValidaCoracoes() {
		
		jogador.setBonusCoracaoGra(0);
		jogador.setBonusCoracaoPeq(2);
		jogador.setCoracaoPeq(0);	
		jogador.setCoracaoGra(0);
					
			boolean valida =  
				RegrasDoJogo.validaCompraCarta(jogador, carta);
		assertEquals(valida, false);

	}
	@Test
	@DisplayName("Teste Compra CartaObjetivo coracoes VERDADEIRO")
	void testValidaCompraCartaObjetivoVerdadeiro() {
		
		boolean valida =  
				RegrasDoJogo.validaCompraCartaObjetivo(jogador);
		assertEquals(valida, true);
		
	}
	
	@Test
	@DisplayName("Teste Compra CartaObjetivo coracoes FALSO")
	void testValidaCompraCartaObjetivoFalso() {
		jogador.setBonusCoracaoGra(0);
		jogador.setBonusCoracaoPeq(0);
		jogador.setCoracaoPeq(0);	
		jogador.setCoracaoGra(0);
		boolean valida =  
				RegrasDoJogo.validaCompraCartaObjetivo(jogador);
		assertEquals(valida, false);
		
	}
	
	
}
