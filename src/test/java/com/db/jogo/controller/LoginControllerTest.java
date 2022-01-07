package com.db.jogo.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import com.db.jogo.model.Admin;
import com.db.jogo.service.AdminService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @MockBean
    private AdminService adminService;

    @Test
    public void testVerificaSenhaSucesso() throws Exception{
        Admin admin = new Admin();
        admin.setId(UUID.randomUUID());
        admin.setSenha("123");
        given(adminService.findBySenha("123")).willReturn(admin);


        this.mockMvc.perform(post("/login")
                        .content(asJsonString(admin))
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.logado").value("true"))
                .andExpect(status().isOk());
    }

    @Test
    public void testVerificaSenhaInvalida() throws Exception{
        Admin admin = new Admin();
        admin.setId(UUID.randomUUID());
        admin.setSenha("123");
        given(adminService.findBySenha("123")).willReturn(admin);

        Admin adminRequest = new Admin();
        adminRequest.setSenha("321");

        this.mockMvc.perform(post("/login")
                        .content(asJsonString(adminRequest))
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.logado").value("false"))
                .andExpect(status().isOk());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
