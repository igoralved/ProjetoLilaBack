package com.db.jogo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import com.db.jogo.exception.JogoInvalidoException;
import com.db.jogo.model.Baralho;
import com.db.jogo.model.CartaDoJogo;
import com.db.jogo.model.CartaInicio;
import com.db.jogo.model.CartaObjetivo;
import com.db.jogo.model.Jogador;
import com.db.jogo.model.Sala;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class WebSocketServiceTest {

    @Mock
    WebSocketService webSocketService;
    
    CartaInicio cartaInicio = new CartaInicio();
    Baralho baralho = new Baralho();
    CartaDoJogo carta = new CartaDoJogo();
    CartaObjetivo cartaObjetivo = new CartaObjetivo();
    Jogador jogador = new Jogador();
    Jogador jogador2 = new Jogador();
    Sala sala = new Sala();

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
        baralho.setCartasInicio(new ArrayList<>());
        baralho.adicionarCartaDoInicio(cartaInicio);
        baralho.setCartasDoJogo(new ArrayList<>());
        baralho.adicionarCartadoJogo(carta);
        baralho.setCartasDeObjetivo(new ArrayList<>());
        baralho.adicionarCartaDoInicio(cartaInicio);

        jogador.setId(UUID.randomUUID());
        jogador.setNome("Felipe");
        jogador.setPontos(0);
        jogador.setBonusCoracaoGra(0);
        jogador.setBonusCoracaoPeq(0);
        jogador.setCoracaoGra(0);
        jogador.setCoracaoPeq(0);
        jogador.setListaDeCartas(new HashSet<>());
        jogador.adicionaCarta(carta);
        jogador.adicionaObjetivo(cartaObjetivo);

        jogador2.setId(UUID.randomUUID());
        jogador2.setNome("Guilherme");
        jogador2.setPontos(2);
        jogador2.setBonusCoracaoGra(3);
        jogador2.setBonusCoracaoPeq(2);
        jogador2.setCoracaoGra(5);
        jogador2.setCoracaoPeq(3);
        jogador2.setListaDeCartas(new HashSet<>());
        jogador2.adicionaCarta(carta);
        jogador2.adicionaObjetivo(cartaObjetivo);

        sala.setId(UUID.randomUUID());
        sala.setBaralho(baralho);
        sala.setHash("hashpraentrar");
        sala.setStatusEnum(Sala.StatusEnum.NOVO);
        sala.setJogadores(new ArrayList<>());
        sala.adicionarJogador(jogador);


    }

    @Test
    @DisplayName("Teste para conectar ao jogo")
    void testConectarJogo() {
        sala.adicionarJogador(jogador2);
        try {
            when(webSocketService.conectarJogo(jogador2, sala.getHash())).thenReturn(Optional.of(sala));
            Sala salaTarget = webSocketService.conectarJogo(jogador2, sala.getHash()).get();
            assertEquals(sala, salaTarget);
        } catch (JogoInvalidoException e) {
            fail("jogo inválido");
        }
    }

    @Test
    @DisplayName("Teste para criar um jogo")
    void testCriarJogo() {
        when(webSocketService.criarJogo(jogador)).thenReturn(sala);;
        assertEquals(sala, webSocketService.criarJogo(jogador));
    }

    @Test
    @DisplayName("Teste para criar um jogo com erro")
    void testCriarJogoComErro() {
        when(webSocketService.criarJogo(null)).thenReturn(null);;
        assertNull(webSocketService.criarJogo(null));
    }

}
