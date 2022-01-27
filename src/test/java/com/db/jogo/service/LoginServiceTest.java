package com.db.jogo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("Login Service Teste")
class LoginServiceTest {

	@Mock
	private LoginService loginService;

	@Test
	public void loginComSucesso() throws Exception {

		when(loginService.verificaSenha("Lil@123")).thenReturn(true);

		assertEquals(true, loginService.verificaSenha("Lil@123"));
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
