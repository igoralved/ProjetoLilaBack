package com.db.jogo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import com.db.jogo.model.Baralho;
import com.db.jogo.model.Jogador;
import com.db.jogo.model.Sala;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class SalaServiceTest {
    @Mock
    private SalaService salaService;

    Baralho baralho = new Baralho("Baralho", "lila", "descricao");

    Jogador jogador = new Jogador(UUID.randomUUID(), "Felipe");

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
         salaService.saveSala(null);
         when(salaService.saveSala(null)).thenReturn(null);
         assertEquals(null, salaService.saveSala(null));
      }
}
