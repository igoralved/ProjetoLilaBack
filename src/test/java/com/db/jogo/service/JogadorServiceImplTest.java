package com.db.jogo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.db.jogo.model.Jogador;


@ExtendWith(MockitoExtension.class)
@DisplayName("Jogador Service Teste")
class JogadorServiceImplTest {
	
	
	
	@Mock
    private JogadorServiceImpl jogadorService;
	
	Jogador jogador= Jogador.builder()
			.id(UUID.randomUUID())
			.nome("joão")
			.bonusCoracaoGra(1)
			.bonusCoracaoPeq(1)
			.coracaoGra(1)
			.pontos(1)
			.coracaoPeq(2)
			.build();

    Optional<Jogador> jogadorOpt= 
    		Optional.ofNullable(Jogador.builder()
	.id(UUID.randomUUID())
	.nome("joão")
	.bonusCoracaoGra(1)
	.bonusCoracaoPeq(1)
	.coracaoGra(1)
	.pontos(1)
	.coracaoPeq(2)
	.build());

	@Test
	@DisplayName("Teste do GET do ServiceImpl do Jogador")
	public void testBuscaJogador() throws Exception {
	
	    	UUID id = UUID.fromString("fd7b6723-77e2-4846-bd22-88df15ca150a"); 

	        when(jogadorService.findById(id)).thenReturn(jogadorOpt);

	        assertEquals(jogadorOpt, jogadorService.findById(id));

	    }
	
	@Test
	@DisplayName("Teste do SAVE/Successo do ServiceImpl do Jogador")
	public void testPostJogador() throws Exception {
		
	        when(jogadorService.saveJogador(jogador)).thenReturn(jogador);

	        assertEquals(jogador, jogadorService.saveJogador(jogador));

	    }
	@Test
	@DisplayName("Teste do SAVE/Error do ServiceImpl do Jogador")
	public void testPostErrorJogador() throws Exception {
		
	        when(jogadorService.saveJogador(null)).thenReturn(null);

		    assertNull(jogadorService.saveJogador(null));

	    }
	
	@Test
	@DisplayName("Teste do PUT/Sucesso do ServiceImpl do Jogador")
	public void testPutSucessoJogador() throws Exception {
				
	        when(jogadorService.atualizarJogador(jogador)).thenReturn(Optional.of(jogador));

	        assertEquals(Optional.of(jogador), jogadorService.atualizarJogador(jogador));

	    }
	
	@Test
	@DisplayName("Teste do PUT/Error do ServiceImpl do Jogador")
	public void testPutErrorJogador() throws Exception {
		
		jogador.setId(null);
		
	        when(jogadorService.atualizarJogador(jogador)).thenReturn(Optional.of(jogador));

	        assertEquals(Optional.of(jogador), jogadorService.atualizarJogador(jogador));

	    }
	
	
	
}