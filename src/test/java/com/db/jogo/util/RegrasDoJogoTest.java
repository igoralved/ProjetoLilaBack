package com.db.jogo.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.UUID;
import com.db.jogo.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.db.jogo.model.CartaDoJogo;
import com.db.jogo.model.Jogador;

class RegrasDoJogoTest {
	
	private CartaDoJogo carta;	
	private Jogador jogador;
	private CartaInicio cartaInicio = new CartaInicio();
    private Baralho baralho = new Baralho();
    private CartaObjetivo cartaObjetivo = new CartaObjetivo();
    private Sala sala = new Sala();
	
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

	@BeforeEach
	public void init(){
        cartaInicio.setId(UUID.randomUUID());
        cartaInicio.setNome("Teste");
        cartaInicio.setDescricao("Descricao");

        cartaObjetivo.setId(UUID.randomUUID());
        cartaObjetivo.setDescricao("Texto da carta");
        cartaObjetivo.setPontos(0);
        cartaObjetivo.setClassificacao("Ganhe pontos");
        cartaObjetivo.setCategoria("Física");

        baralho.setId(UUID.randomUUID());
        baralho.setCodigo("LILA");
        baralho.setTitulo("Teste");
        baralho.setDescricao("Exemplo");
        baralho.setCartaInicio(new ArrayList<>());
        baralho.adicionarCartaDoInicio(cartaInicio);
        baralho.setCartasDoJogo(new ArrayList<>());
        baralho.adicionarCartadoJogo(carta);
        baralho.setCartasObjetivo(new ArrayList<>());
        baralho.adicionarCartaDoInicio(cartaInicio);

        sala.setId(UUID.randomUUID());
        sala.setBaralho(baralho);
        sala.setHash("hashpraentrar");
        sala.setStatusEnum(Sala.StatusEnum.NOVO);
        sala.setJogadores(new ArrayList<>());
        sala.adicionarJogador(jogador);
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

	@Test
	@DisplayName("Teste jogador com oito pontos, status do jogo ULTIMA_RODADA")
	void testValidaJogadorComOitoPontos() {
		jogador.setPontos(8);
		RegrasDoJogo.verificaJogadorSeTemOitoPontos(jogador, sala);
		assertEquals("ULTIMA_RODADA", sala.getStatusEnum().name());
	}
	
	@Test
	@DisplayName("Teste jogador com menos de oito pontos, status do jogo NOVO")
	void testValidaJogadorComMenosDeOitoPontos() {
		jogador.setPontos(6);
		RegrasDoJogo.verificaJogadorSeTemOitoPontos(jogador, sala);
		// TODO: Aguardando método que altera status do jogo. Alterar status para JOGANDO.
		assertEquals("NOVO", sala.getStatusEnum().name());
	}
}
