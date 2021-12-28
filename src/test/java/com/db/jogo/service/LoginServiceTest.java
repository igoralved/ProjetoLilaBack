package com.db.jogo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.db.jogo.model.Admin;
import com.db.jogo.service.AdminService;
import com.db.jogo.service.LoginService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoginServiceTest {

	@Autowired
	private LoginService loginService;

	@Test
	public void loginComSucesso() throws Exception {

		boolean actual = loginService.verificaSenha("Lil@123");

		assertEquals(true, actual);
	}

	@Test
	public void loginComErro() throws Exception {

		boolean actual = loginService.verificaSenha("987");

		assertEquals(false, actual);
	}

	@Test
	public void loginComSenhaNula() throws Exception {

		boolean actual = loginService.verificaSenha(null);

		assertEquals(false, actual);
	}

	@Test
	public void loginComSenhaVazia() throws Exception {

		boolean actual = loginService.verificaSenha("");

		assertEquals(false, actual);
	}

}
