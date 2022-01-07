package com.db.jogo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import java.util.UUID;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.db.jogo.model.Jogador;
import com.db.jogo.service.JogadorServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Jogador Controller Teste")
class JogadorControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	@MockBean
	JogadorServiceImpl jogadorService;

	String id = "d1516d33-ff6f-4dc9-aedf-9316421096cb";
	Jogador jogador = Jogador.builder().id(UUID.fromString(id)).build();

	@Test
	@DisplayName("Teste do POST/Sucesso do Controller do Jogador")
	public void deveRetornarSucesso_QuandoCriarJogador() throws Exception {

		when(jogadorService.saveJogador(any(Jogador.class))).thenReturn(jogador);

		ObjectMapper mapper = new ObjectMapper();
		String jogadorComoJSON = mapper.writeValueAsString(jogador);

		mockMvc.perform(MockMvcRequestBuilders.post("/jogador").content(jogadorComoJSON)
				.accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated()).andExpect(content().json(jogadorComoJSON));

	}

	@Test
	@DisplayName("Teste do POST/Error do Controller do Jogador")
	public void deveRetornarErro_QuandoCriarJogadorInvalido() throws Exception {

		when(jogadorService.saveJogador(any(Jogador.class))).thenReturn(jogador);

		mockMvc.perform(MockMvcRequestBuilders.post("/jogador").accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isBadRequest());

	}

	@Test
	@DisplayName("Teste do PUT/Erro do Controller do Jogador")
	public void deveRetornarErro_QuandoAtualizarJogador() throws Exception {

		when(jogadorService.saveJogador(any(Jogador.class))).thenReturn(jogador);

		ObjectMapper mapper = new ObjectMapper();
		String jogadorParaAtualizarComoJSON = mapper.writeValueAsString(jogador);

		mockMvc.perform(MockMvcRequestBuilders.put("/jogador").content(jogadorParaAtualizarComoJSON)
				.accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isNotFound());

	}

	@Test
	@DisplayName("Teste do GET do Controller do Jogador")
	public void deveRetornarSucesso_QuandoBuscar() throws Exception {

		given(jogadorService.findById(jogador.getId())).willReturn(Optional.of(jogador));

		ObjectMapper mapper = new ObjectMapper();
		String jogadorComoJSON = mapper.writeValueAsString(jogador);
		mockMvc.perform(get("/jogador/" + jogador.getId()).content(jogadorComoJSON)
				.accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(content().json(jogadorComoJSON));

	}

}
