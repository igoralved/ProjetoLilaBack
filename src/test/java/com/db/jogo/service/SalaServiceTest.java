package com.db.jogo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    Baralho baralho = new Baralho("Baralho", "lila", "descricao");
    CartaDoJogo carta = new CartaDoJogo();
    CartaDeObjetivo cartaObjetivo = new CartaDeObjetivo();
    Jogador jogador = new Jogador();

    @BeforeEach
    public void init(){
        carta.setId(UUID.randomUUID());
        carta.setPontos(2);
        carta.setBonus(true);
        carta.setCategoria("Visual");
        carta.setTexto("Deficiencia visual");
        carta.setFonte("Wikipedia");
        carta.setBaralho(baralho);
        carta.setValorCorGrande(0);
        carta.setValorCorPequeno(0);
        carta.setTipo("Ação");

        cartaObjetivo.setId(UUID.randomUUID());
        cartaObjetivo.setDescricao("Objetivo");
        cartaObjetivo.setPontos(0);
        cartaObjetivo.setBaralho(baralho);
        cartaObjetivo.setClassificacao("Genérica");
        cartaObjetivo.setCategoria("Exemplo");

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
    }



    Optional<Sala> salaLocalizada;


    @Test
    @DisplayName("Teste para encontrar uma sala por Hash")
    void encontrarSalaPorHash() throws Exception {
        Sala sala = new Sala();
        sala.setId(UUID.randomUUID());
        sala.setBaralho(baralho);
        sala.setHash("hashpraentrar");
        sala.setStatusEnum(Sala.StatusEnum.NOVO);
        sala.setJogadores(new ArrayList<>());
        sala.adicionarJogador(jogador);
        salaLocalizada = salaService.findSalaByHash("iuervnijr0f");
        assertEquals(salaLocalizada, salaService.findSalaByHash("iuervnijr0f"));
    }

     @DisplayName("Teste para criar uma sala do Service")
     @Test
     void criarSala() throws Exception {
         Sala sala = new Sala();
         sala.setId(UUID.randomUUID());
         sala.setBaralho(baralho);
         sala.setHash("hashpraentrar");
         sala.setStatusEnum(Sala.StatusEnum.NOVO);
         sala.setJogadores(new ArrayList<>());
         sala.adicionarJogador(jogador);
         when(salaService.saveSala(sala)).thenReturn(sala);;
         assertEquals(sala, salaService.saveSala(sala));
     }

     @DisplayName("Teste de erro do retorno da sala")
     @Test
     void encontrarSalaPorHashComErro() throws Exception {
         Sala sala = new Sala();
         sala.setId(UUID.randomUUID());
         sala.setBaralho(baralho);
         sala.setHash("hashpraentrar");
         sala.setStatusEnum(Sala.StatusEnum.NOVO);
         sala.setJogadores(new ArrayList<>());
         sala.adicionarJogador(jogador);
         salaLocalizada = salaService.findSalaByHash("ertfvygbhnj");
         assertEquals(salaLocalizada, salaService.findSalaByHash("iuervnijr0f"));
     }
 
     @DisplayName("Teste de erro do SAVE do Service")
     @Test
      void criarSalaComErro() throws Exception {
         when(salaService.saveSala(null)).thenReturn(null);
         assertEquals(null, salaService.saveSala(null));
      }
}
