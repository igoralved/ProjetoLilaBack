package com.db.jogo.util;

import com.db.jogo.controller.BaralhoController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@WebAppConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(Dado.class)
@DisplayName("Baralho Controller Teste")
public class DadoTest {

    @Autowired
    MockMvc mockMvc;

    Dado  dado = new Dado();
    int dadoUm;
    int dadoDois;
    int dadoTrês;
    int dadoQuatro;
    int dadoCinco;
    int dadoSeis;

    @Test
    @DisplayName("teste")
    public void resultadoDoDado(){



    }

}
