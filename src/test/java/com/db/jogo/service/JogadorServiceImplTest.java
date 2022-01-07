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
	
	Jogador jogador= Jogador.builder().build();

    Optional<Jogador> jogadorOpt= Optional.of(jogador);

	@Test
	@DisplayName("Teste do GET do Service do Jogador")
	public void testBuscaJogador() throws Exception {
	
	    	UUID id = UUID.fromString("fd7b6723-77e2-4846-bd22-88df15ca150a"); 

	        when(jogadorService.findById(id)).thenReturn(jogadorOpt);

	        assertEquals(jogadorOpt, jogadorService.findById(id));

	    }
	
	@Test
	@DisplayName("Teste do SAVE do Service do Jogador")
	public void testPostJogador() throws Exception {
		
	        when(jogadorService.saveJogador(jogador)).thenReturn(jogador);

	        assertEquals(jogador, jogadorService.saveJogador(jogador));

	    }
	
}