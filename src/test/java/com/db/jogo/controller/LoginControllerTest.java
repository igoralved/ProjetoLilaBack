package com.db.jogo.controller;

import static org.mockito.BDDMockito.given;

import java.util.UUID;

import com.db.jogo.model.Admin;
import com.db.jogo.service.AdminService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {
    
    @Autowired
    private LoginController loginController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @MockBean
	private AdminService adminService;

    @Test
    public void testVerificaSenha() throws Exception{
        Admin admin = new Admin();
        admin.setId(UUID.randomUUID());
        admin.setSenha("123");
        given(adminService.findBySenha("123")).willReturn(admin);
        

        this.mockMvc.perform(get("/login")
            .content(asJsonString(admin))
            .accept(MediaType.APPLICATION_JSON_VALUE)  
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("true"))
            .andExpect(status().isOk());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

}
}
