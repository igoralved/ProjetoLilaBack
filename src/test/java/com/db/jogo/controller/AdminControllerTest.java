package com.db.jogo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import com.db.jogo.model.Admin;
import com.db.jogo.service.AdminService;
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


@WebAppConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(AdminController.class)
@DisplayName("Admin Controller Teste")
class AdminControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	AdminService adminService;
	
	@Test
	@DisplayName("Teste do POST do Controller do Admin")
	public void testCriacaoAdmin() throws Exception {
		Admin newAdmin = Admin.builder().senha("123")
				.id(UUID.randomUUID())
				.build();

		ObjectMapper mapper = new ObjectMapper();
		String newAdminAsJSON = mapper.writeValueAsString(newAdmin);
		this.mockMvc.perform(post("/admin")
				.content(newAdminAsJSON)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated());
	}
}