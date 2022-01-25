package com.db.jogo.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import java.util.UUID;

import com.db.jogo.model.CartaObjetivo;
import com.db.jogo.service.CartaObjetivoServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebAppConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(CartaObjetivoController.class)
@DisplayName("Carta Objetivo Controller Teste")
class CartaObjetivoControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	CartaObjetivoServiceImpl cartaObjetivoService;
	String id = "d1516d33-ff6f-4dc9-aedf-9316421096cb";
	CartaObjetivo newCartaObjetivo = CartaObjetivo.builder().id(UUID.fromString(id)).classificacao("Deficiencia Física")
			.categoria("Filmes").pontos(3).descricao("Exemplo descrição").build();

	@Test
	@DisplayName("Teste do POST do Controller do Carta Objetivo")
	public void testCriacaoCartaObjetivo() throws Exception {
		CartaObjetivoServiceImpl cartaObjetivoService;

		CartaObjetivo newCartaObjetivo = CartaObjetivo.builder().id(UUID.randomUUID())
				.classificacao("Deficiencia Visual").categoria("Filmes").pontos(1).descricao("Exemplo descrição")
				.build();

		ObjectMapper mapper = new ObjectMapper();

		String newCartaObjetivoAsJSON = mapper.writeValueAsString(newCartaObjetivo);
		this.mockMvc.perform(post("/cartaobjetivo").content(newCartaObjetivoAsJSON)
				.accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated());
	}

	@Test
	@DisplayName("Teste do GET do Controller da Carta Objetivo")
	public void deveRetornarSucesso_QuandoBuscar() throws Exception {

		given(cartaObjetivoService.findById(newCartaObjetivo.getId())).willReturn(Optional.of(newCartaObjetivo));

		ObjectMapper mapper = new ObjectMapper();
		String cartaComoJSON = mapper.writeValueAsString(newCartaObjetivo);

		mockMvc.perform(get("/cartaobjetivo/" + newCartaObjetivo.getId())
				.accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.content().json(cartaComoJSON));

	}
}