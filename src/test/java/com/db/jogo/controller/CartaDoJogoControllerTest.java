package com.db.jogo.controller;

import com.db.jogo.model.CartaDoJogo;

import com.db.jogo.service.CartaDoJogoServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.postgresql.hostchooser.HostRequirement.any;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Carta Do Jogo Controller Teste")
public class CartaDoJogoControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	CartaDoJogoServiceImpl cartaDoJogoService;

	String id = "d1516d33-ff6f-4dc9-aedf-9316421096cb";
	CartaDoJogo newCartaDoJogo = CartaDoJogo.builder().id(UUID.fromString(id)).bonus(true).valorCorGrande(1)
			.valorCorPequeno(0).categoria("Fisica").fonte("Google").tipo("Informação").pontos(3).texto("Teste").build();

	@Test
	@DisplayName("Teste do POST/Sucesso do Controller Carta do Jogo")
	public void deveRetornarSucesso_QuandoCriarCartaDoJogo() throws Exception {

		when(cartaDoJogoService.saveCartaDoJogo(any(CartaDoJogo.class))).thenReturn(newCartaDoJogo);

		ObjectMapper mapper = new ObjectMapper();
		String cartaDoJogoJSON = mapper.writeValueAsString(newCartaDoJogo);

		mockMvc.perform(MockMvcRequestBuilders.post("/cartadojogo").content(cartaDoJogoJSON)
				.accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated()).andExpect(MockMvcResultMatchers.content().json(cartaDoJogoJSON));
	}

	@Test
	@DisplayName("Teste do POST/Error do Controller da Carta Do Jogo")
	public void deveRetornarErro_QuandoCriarCartaDoJogoInvalido() throws Exception {

		when(cartaDoJogoService.saveCartaDoJogo(any(CartaDoJogo.class))).thenReturn(newCartaDoJogo);

		mockMvc.perform(MockMvcRequestBuilders.post("/cartadojogo").accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isBadRequest());

	}

	@Test
	@DisplayName("Teste do GET do Controller Carta Do jogo")
	public void deveRetornarSucesso_QuandoBuscarCartaDoJogo() throws Exception {

		given(cartaDoJogoService.findById(newCartaDoJogo.getId())).willReturn(Optional.of(newCartaDoJogo));

		ObjectMapper mapper = new ObjectMapper();
		String cartaDoJogoJSON = mapper.writeValueAsString(newCartaDoJogo);

		mockMvc.perform(get("/cartadojogo/" + newCartaDoJogo.getId()).content(cartaDoJogoJSON)
				.accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.content().json(cartaDoJogoJSON));

	}

}