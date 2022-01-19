package com.db.jogo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.db.jogo.model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class SalaServiceTest {

    @Mock
    private SalaService salaService;

    @Mock
    private SalaService salaVazia;

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

        baralho.setId(UUID.randomUUID());
        baralho.setCodigo("LILA");
        baralho.setTitulo("Teste");
        baralho.setDescricao("Exemplo");
        baralho.setCartaInicio(new ArrayList<>());
        baralho.adicionarCartaDoInicio(cartaInicio);
        baralho.setCartasDoJogo(new ArrayList<>());
        baralho.adicionarCartadoJogo(carta);
        baralho.setCartasObjetivo(new ArrayList<>());
        baralho.adicionarCartaDoInicio(cartaInicio);

        //jogador 1
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

        //jogador 2
        jogador2.setId(UUID.randomUUID());
        jogador2.setNome("Igor");
        jogador2.setPontos(1);
        jogador2.setBonusCoracaoGra(2);
        jogador2.setBonusCoracaoPeq(1);
        jogador2.setCoracaoGra(1);
        jogador2.setCoracaoPeq(1);
        jogador2.setListaDeCartas(new HashSet<>());
        jogador2.adicionaCarta(carta);
        jogador2.adicionaObjetivo(cartaObjetivo);

        sala.setId(UUID.randomUUID());
        sala.setBaralho(baralho);
        sala.setHash("hashpraentrar");
        sala.setStatusEnum(Sala.StatusEnum.NOVO);
        sala.setJogadores(new ArrayList<>());
        sala.adicionarJogador(jogador);
        sala.adicionarJogador(jogador2);
    }



    Optional<Sala> salaLocalizada;

    @Test
    @DisplayName("Teste para encontrar uma sala por Hash")
    void encontrarSalaPorHash() {
        salaLocalizada = salaService.findSalaByHash("iuervnijr0f");
        assertEquals(salaLocalizada, salaService.findSalaByHash("iuervnijr0f"));
    }

    @DisplayName("Teste para criar uma sala do Service")
    @Test
    void criarSala(){
        when(salaService.saveSala(sala)).thenReturn(sala);;
        assertEquals(sala, salaService.saveSala(sala));
    }

    @DisplayName("Teste de erro do retorno da sala")
    @Test
    void encontrarSalaPorHashComErro() {
        salaLocalizada = salaService.findSalaByHash("ertfvygbhnj");
        assertFalse(salaLocalizada.isPresent());
    }

    @DisplayName("Teste de erro do SAVE do Service")
    @Test
    void criarSalaComErro(){
        when(salaService.saveSala(null)).thenReturn(null);
        assertNull(salaService.saveSala(null));
    }

    @Test
    @DisplayName("Teste de número de jogadores na sala")
    void testarNumeroJogadores() {
        when(salaService.totalJogadores("ertfvygbhnj")).thenReturn(2);
        assertEquals(2, salaService.totalJogadores("ertfvygbhnj"));
    }

    @Test
    @DisplayName("Teste de quem é o primeiro jogador (host)")
    void testarPrimeiroJogador() {
        when(salaService.findFirst("ertfvygbhnj")).thenReturn(jogador);
        assertEquals(jogador, salaService.findFirst("ertfvygbhnj"));
    }

    @Test
    @DisplayName("Testa quem é o primeiro jogador quando a sala está vazia")
    void testaPrimeiroEmSalaVazia() {
        when(salaVazia.findFirst(sala.getHash())).thenReturn(jogador);
        assertEquals(jogador, salaVazia.findFirst(sala.getHash()));
    }

    @Test
    @DisplayName("Teste de encontrar sala vazia")
    void testaSalaNula() {
        when(salaService.findFirst(null)).thenReturn(null);
        assertNull(salaService.findFirst(null));
    }

}