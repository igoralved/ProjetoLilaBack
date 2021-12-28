package com.db.jogo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.db.jogo.model.Admin;
import com.db.jogo.model.Baralho;
import com.db.jogo.service.AdminServiceImpl;
import com.db.jogo.service.BaralhoServiceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(BaralhoController.class)
@DisplayName("Baralho Controller Teste")
class BaralhoControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	BaralhoServiceImpl baralhoService;
	
	@Test
	@DisplayName("Teste do POST do Controller do Baralho")
	public void testCriacaoBaralho() throws Exception {
		Baralho newBaralho = Baralho.builder().codigo("LILA1")
				.titulo("Corações de Lila")
				.descricao("Jogo de cartas.")
				.build();

		ObjectMapper mapper = new ObjectMapper();

		String newBaralhoAsJSON = mapper.writeValueAsString(newBaralho);
		this.mockMvc.perform(post("/baralho").content(newBaralhoAsJSON).accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated());
	}
	

}
