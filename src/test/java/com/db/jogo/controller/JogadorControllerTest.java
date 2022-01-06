package com.db.jogo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.db.jogo.model.Admin;
import com.db.jogo.model.Jogador;
import com.db.jogo.service.JogadorServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebAppConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(JogadorController.class)
@DisplayName("Jogador Controller Teste")
class JogadorControllerTest {
	
	@Autowired
	MockMvc mockMvc;

	@MockBean
	JogadorServiceImpl jogadorService;
	
	@Test
	@DisplayName("Teste do POST do Controller do Jogador")
	public void testCriacaoJogador() throws Exception {
		Jogador novoJogador = Jogador.builder()
				.id(UUID.randomUUID())
				.build();

		ObjectMapper mapper = new ObjectMapper();
		String novoJogadorComoJSON = mapper.writeValueAsString(novoJogador);
		this.mockMvc.perform(post("/jogador")
				.content(novoJogadorComoJSON)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated());
	}
	
	
	
	}


	




	
