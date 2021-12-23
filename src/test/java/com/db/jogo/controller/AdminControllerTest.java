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
import com.db.jogo.service.AdminServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;



@RunWith(SpringRunner.class)
@WebAppConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(AdminController.class)
@DisplayName("Admin Controller Teste")
class AdminControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	AdminServiceImpl adminService;
	
	@Test
	@DisplayName("Teste do POST do Controller do Admin")
	public void testCriacaoAdmin() throws Exception {
		Admin newAdmin = Admin.builder().senha("123")
				.id(UUID.fromString("13ab5ac1-2f63-44db-90d9-2a20ceda6a00"))
				.build();

		ObjectMapper mapper = new ObjectMapper();

		String newAdminAsJSON = mapper.writeValueAsString(newAdmin);

		this.mockMvc.perform(post("/admin").content(newAdminAsJSON).accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated());
	}

}