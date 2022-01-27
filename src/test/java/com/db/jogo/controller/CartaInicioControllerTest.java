package com.db.jogo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.db.jogo.model.CartaInicio;
import com.db.jogo.service.CartaInicioService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebAppConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(CartaInicioController.class)
@DisplayName("Carta Inicio Controller Teste")
class CartaInicioControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	CartaInicioService cartaInicioService;

	String id = "d1516d33-ff6f-4dc9-aedf-9316421096cb";
	CartaInicio newCartaInicio = CartaInicio.builder()
			.nome("testeNome")
			.descricao("testeDescrição")
			.id(UUID.fromString(id))
			.build();

	@Test
	@DisplayName("Teste do POST do Controller da Carta Inicio")
	public void testCriacaoCartaInicio() throws Exception {
		
		when(cartaInicioService.saveCartaInicio(any(CartaInicio.class))).thenReturn(newCartaInicio);
		ObjectMapper mapper = new ObjectMapper();

		String newcartaInicioAsJSON = mapper.writeValueAsString(newCartaInicio);
		
		this.mockMvc.perform(post("/cartainicio").content(newcartaInicioAsJSON).accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated());
	}

	@Test
	@DisplayName("Teste do GET do Controller Carta Inicio")
	public void deveRetornarSucesso_QuandoBuscar() throws Exception {

		ArrayList<CartaInicio> CartaInicio = new ArrayList<>();

		given(cartaInicioService.findAllCartaInicio()).willReturn(CartaInicio);

		ObjectMapper mapper = new ObjectMapper();
		String listaCartaInicioComoJSON = mapper.writeValueAsString(CartaInicio);
	
		mockMvc.perform(get("/cartainicio/listar")
				.accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.content().json(listaCartaInicioComoJSON));

	}
	
	@Test
	@DisplayName("Teste do GET do Controller Carta Inicio")
	public void deveRetornarSucesso_QuandoBuscarLista() throws Exception {
		given(cartaInicioService.findById(newCartaInicio.getId())).willReturn(Optional.of(newCartaInicio));

		ObjectMapper mapper = new ObjectMapper();

		String cartaInicioJSON = mapper.writeValueAsString(newCartaInicio);
		mockMvc.perform(get("/cartainicio/" + newCartaInicio.getId()).content(cartaInicioJSON)
				.accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.content().json(cartaInicioJSON));
	}
	
}