package com.db.jogo.service;

<<<<<<< HEAD

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

=======
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

>>>>>>> origin/US055/US059-compra-um-coracao-grande
import com.db.jogo.dto.SalaRequest;
import com.db.jogo.dto.SalaResponse;
import com.db.jogo.exception.JogoInvalidoException;
import com.db.jogo.model.Baralho;
import com.db.jogo.model.CartaDoJogo;
import com.db.jogo.model.CartaInicio;
import com.db.jogo.model.CartaObjetivo;
import com.db.jogo.model.Jogador;
import com.db.jogo.model.Sala;

<<<<<<< HEAD


=======
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
>>>>>>> origin/US055/US059-compra-um-coracao-grande

@ExtendWith(MockitoExtension.class)
public class WebSocketServiceTest {

    @Mock
    WebSocketServiceImpl webSocketServiceImpl;
    
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
        carta.setValorCorGrande(0);
        carta.setValorCorPequeno(0);
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
<<<<<<< HEAD

        baralho.setCartasInicio(new ArrayList<CartaInicio>());
        baralho.adicionarCartaDoInicio(cartaInicio);
        baralho.setCartasDoJogo(new ArrayList<CartaDoJogo>());
        baralho.adicionarCartadoJogo(carta);
        baralho.setCartasObjetivo(new ArrayList<CartaObjetivo>());

=======
        baralho.setCartasInicio(new ArrayList<>());
        baralho.adicionarCartaDoInicio(cartaInicio);
        baralho.setCartasDoJogo(new ArrayList<>());
        baralho.adicionarCartadoJogo(carta);
        baralho.setCartasObjetivo(new ArrayList<>());
>>>>>>> origin/US055/US059-compra-um-coracao-grande
        baralho.adicionarCartaDoInicio(cartaInicio);

        jogador.setId(UUID.randomUUID());
        jogador.setNome("Felipe");
        jogador.setPontos(0);
        jogador.setBonusCoracaoGra(0);
        jogador.setBonusCoracaoPeq(0);
        jogador.setCoracaoGra(0);
        jogador.setCoracaoPeq(0);
        jogador.adicionaCarta(carta);
        jogador.adicionaObjetivo(cartaObjetivo);

        jogador2.setId(UUID.randomUUID());
        jogador2.setNome("Guilherme");
        jogador2.setPontos(2);
        jogador2.setBonusCoracaoGra(3);
        jogador2.setBonusCoracaoPeq(2);
        jogador2.setCoracaoGra(5);
        jogador2.setCoracaoPeq(3);
        jogador2.adicionaCarta(carta);
        jogador2.adicionaObjetivo(cartaObjetivo);

        sala.setId(UUID.randomUUID());
        sala.setBaralho(baralho);
        sala.setHash("hashpraentrar");
<<<<<<< HEAD

        sala.setStatus(Sala.StatusEnum.NOVO);
        sala.setJogadores(new ArrayList<Jogador>());

=======
        sala.setStatusEnum(Sala.StatusEnum.NOVO);
        sala.setJogadores(new ArrayList<>());
>>>>>>> origin/US055/US059-compra-um-coracao-grande
        sala.adicionarJogador(jogador);

        salaRequest.setHash("hashpraentrar");
        salaRequest.setJogador(jogador2);

        salaResponse.setSala(sala);
        salaResponse.setJogador(jogador);
    }

    @Test
    @DisplayName("Teste para conectar ao jogo")
    void testConectarJogo() {
        sala.adicionarJogador(jogador2);
        try {
            when(webSocketServiceImpl.conectarJogo(jogador2, sala.getHash())).thenReturn(salaResponse);
            SalaResponse salaTarget = webSocketServiceImpl.conectarJogo(jogador2, sala.getHash());
            assertEquals(salaResponse, salaTarget);
        } catch (JogoInvalidoException e) {
            fail("Parametros nulos");
        }
    }

    @Test
    @DisplayName("Teste para conectar ao jogo com jogador nulo")
    void testConectarJogoComJogadorNull() throws JogoInvalidoException {

        when(webSocketServiceImpl.conectarJogo(null, sala.getHash())).thenReturn(salaResponse);
        assertEquals(salaResponse, webSocketServiceImpl.conectarJogo(null, sala.getHash()));
    }

    @Test
    @DisplayName("Teste para criar um jogo")
    void testCriarJogo() throws JogoInvalidoException {
        when(webSocketServiceImpl.criarJogo(jogador)).thenReturn(salaResponse);;
        assertEquals(salaResponse, webSocketServiceImpl.criarJogo(jogador));
    }

    @Test
    @DisplayName("Teste para não criar jogo com parametro null")
    void testCriarJogoComErro() throws JogoInvalidoException {
        when(webSocketServiceImpl.criarJogo(null)).thenReturn(null);;
        assertNull(webSocketServiceImpl.criarJogo(null));
    }

    @Test
    @DisplayName("Teste para criar um jogador")
    void testCriaJogador() {
        when(webSocketServiceImpl.criarJogador(jogador)).thenReturn(jogador);;
        assertEquals(jogador, webSocketServiceImpl.criarJogador(jogador));
    }

    @Test
    @DisplayName("Teste para não criar jogador com parametro null")
    void testCriaJogadorComErro()  {
        when(webSocketServiceImpl.criarJogador(null)).thenReturn(null);;
        assertNull(webSocketServiceImpl.criarJogador(null));
    }

}
