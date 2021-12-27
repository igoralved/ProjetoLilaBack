// package com.db.jogo.controller;

// import java.util.UUID;

// import com.db.jogo.model.Admin;
// import com.db.jogo.service.AdminService;

// import org.junit.jupiter.api.Test;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.test.context.TestPropertySource;
// import org.springframework.test.context.junit4.SpringRunner;
// import org.springframework.test.web.servlet.MockMvc;

// @RunWith(SpringRunner.class)
// @SpringBootTest(webEnvironment = WebEnvironment.MOCK)
// @AutoConfigureMockMvc
// @TestPropertySource(locations = "classpath:application-integrationtest.properties")
// public class LoginControllerTest {
    
//     @Autowired
//     private LoginController loginController;

//     @Autowired
//     private MockMvc mvc;

//     @MockBean
// 	private AdminService adminService;

//    @Test
//     public void testVerificaSenha() throws Exception {
//         Admin admin = new Admin();
//         admin.setId(UUID.randomUUID());
//         admin.setSenha("123");
//         given(adminService.findBySenha("123")).willReturn(admin);
    
//     }

// }
