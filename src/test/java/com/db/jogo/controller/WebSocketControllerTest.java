package com.db.jogo.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import com.db.jogo.dto.SalaRequest;
import com.db.jogo.dto.SalaResponse;
import com.db.jogo.model.Baralho;
import com.db.jogo.model.CartaDoJogo;
import com.db.jogo.model.CartaInicio;
import com.db.jogo.model.CartaObjetivo;
import com.db.jogo.model.Jogador;
import com.db.jogo.model.Sala;
import com.db.jogo.service.WebSocketServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Websocket Controller Teste")
public class WebSocketControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    @MockBean
    WebSocketController webSocketController;

    @Autowired
    @MockBean
    private WebSocketServiceImpl webSocketServiceImpl;

    CartaInicio cartaInicio = new CartaInicio();
    Baralho baralho = new Baralho();
    CartaDoJogo carta = new CartaDoJogo();
    CartaObjetivo cartaObjetivo = new CartaObjetivo();
    Jogador jogador = new Jogador();
    Jogador jogador2 = new Jogador();
    Sala sala = new Sala();
    SalaRequest salaRequest = new SalaRequest();
    SalaResponse salaResponse = new SalaResponse();

    @BeforeEach
    public void init(){
        cartaInicio.setId(UUID.randomUUID());
        cartaInicio.setNome("Teste");
        cartaInicio.setDescricao("Descricao");

        carta.setId(UUID.randomUUID());
        carta.setPontos(2);
        carta.setBonus(true);
        carta.setCategoria("Visual");
        carta.setTexto("Deficiencia visual");
        carta.setFonte("Wikipedia");
        carta.setValorCorGrande(2);
        carta.setValorCorPequeno(2);
        carta.setTipo("Ação");

        cartaObjetivo.setId(UUID.randomUUID());
        cartaObjetivo.setDescricao("Texto da carta");
        cartaObjetivo.setPontos(0);
        cartaObjetivo.setClassificacao("Ganhe pontos");
        cartaObjetivo.setCategoria("Física");

        baralho.setCodigo("qwerty");
        baralho.setId(UUID.randomUUID());
        baralho.setTitulo("Teste");
        baralho.setDescricao("Exemplo");
        baralho.setCartasInicio(new ArrayList<>());
        baralho.adicionarCartaDoInicio(cartaInicio);
        baralho.setCartasDoJogo(new ArrayList<>());
        baralho.adicionarCartadoJogo(carta);
        baralho.setCartasObjetivo(new ArrayList<>());
        baralho.adicionarCartaDoInicio(cartaInicio);

        jogador.setId(UUID.randomUUID());
        jogador.setNome("Felipe");
        jogador.setPontos(2);
        jogador.setBonusCoracaoGra(3);
        jogador.setBonusCoracaoPeq(2);
        jogador.setCoracaoGra(5);
        jogador.setCoracaoPeq(3);
        jogador.setCartasDoJogo(new ArrayList());
        jogador.adicionaCarta(carta);
        jogador.adicionaObjetivo(cartaObjetivo);

        jogador2.setId(UUID.randomUUID());
        jogador2.setNome("Guilherme");
        jogador2.setPontos(2);
        jogador2.setBonusCoracaoGra(3);
        jogador2.setBonusCoracaoPeq(2);
        jogador2.setCoracaoGra(5);
        jogador2.setCoracaoPeq(3);
        jogador2.setCartasDoJogo(new ArrayList());
        jogador2.adicionaCarta(carta);
        jogador2.adicionaObjetivo(cartaObjetivo);

        sala.setId(UUID.randomUUID());
        sala.setBaralho(baralho);
        sala.setHash("hashpraentrar");
        sala.setStatusEnum(Sala.StatusEnum.NOVO);
        sala.setJogadores(new ArrayList<>());
        sala.adicionarJogador(jogador);

        salaRequest.setHash("hashpraentrar");
        salaRequest.setJogador(jogador2);

        salaResponse.setSala(sala);
        salaResponse.setJogador(jogador);
    }

    @Test
    @DisplayName("Teste para iniciar o jogo")
    void testIniciarJogo() throws Exception {

        given(webSocketServiceImpl.criarJogo(jogador)).willReturn(new SalaResponse());

        ObjectMapper mapper = new ObjectMapper();
        String jogadorAsJSON = mapper.writeValueAsString(jogador);
        this.mockMvc.perform(post("/api/iniciar")
                        .content(jogadorAsJSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().isOk());
    }
  
    @Test
    @DisplayName("Teste para conectar outro jogador")
    void testConectar() throws Exception{
        sala.adicionarJogador(jogador2);

        given(webSocketServiceImpl.conectarJogo( salaRequest.getJogador(), salaRequest.getHash())).willReturn(salaResponse);

        ObjectMapper mapper = new ObjectMapper();
        String newConexaoAsJSON = mapper.writeValueAsString(salaResponse);
        this.mockMvc.perform(post("/api/conectar")
                .content(newConexaoAsJSON)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Teste de BadRequest para iniciar o jogo")
    void testIniciarJogoNull() throws Exception {

        given(webSocketServiceImpl.criarJogo(null)).willReturn(null);

        ObjectMapper mapper = new ObjectMapper();
        String jogadorAsJSON = mapper.writeValueAsString(null);
        this.mockMvc.perform(post("/api/iniciar")
                        .content(jogadorAsJSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Teste de BadRequest para conexão")
    void testConectarComErro() throws Exception{
        Jogador jogadorVazio = new Jogador();
        Sala salaVazia = new Sala();
        given(webSocketServiceImpl.conectarJogo( jogadorVazio, salaVazia.getHash())).willReturn(null);

        ObjectMapper mapper = new ObjectMapper();
        String newConexaoAsJSON = mapper.writeValueAsString(null);
        this.mockMvc.perform(post("/api/conectar")
                        .content(newConexaoAsJSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().isBadRequest());
    }
}
