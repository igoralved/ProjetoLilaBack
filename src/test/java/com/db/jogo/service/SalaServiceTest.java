package com.db.jogo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import com.db.jogo.model.Baralho;
import com.db.jogo.model.Sala;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class SalaServiceTest {
    @Mock
    private SalaService salaService;

    Baralho baralho = Baralho.builder().id_codigo("LILA1").build();

    Optional<Sala> salaLocalizada;
    Sala sala = Sala.builder().hash("iuervnijr0f")
    .baralho(baralho)
    .id(UUID.randomUUID())
    .build();

    @Test
    @DisplayName("Deve retornar uma sala")
    void testFindSalaByHash() throws Exception {
        salaLocalizada = salaService.findSalaByHash("iuervnijr0f");
        assertEquals(salaLocalizada, salaService.findSalaByHash("iuervnijr0f"));
    }

     @DisplayName("Teste do SAVE do Service")
     @Test
     void testSaveSala() throws Exception {
         when(salaService.saveSala(sala)).thenReturn(sala);;
         assertEquals(sala, salaService.saveSala(sala));
     }

     @DisplayName("Teste de erro do retorno da sala")
     @Test
     void testErrorFindSalaByHash() throws Exception {
         salaLocalizada = salaService.findSalaByHash("ertfvygbhnj");
         assertEquals(salaLocalizada, salaService.findSalaByHash("iuervnijr0f"));
     }
 
     @DisplayName("Teste de erro do SAVE do Service")
     @Test
      void testErrorSaveSala() throws Exception {
          Sala sala = salaService.saveSala(null);
          when(salaService.saveSala(null)).thenReturn(sala);
          assertNotEquals(true, salaService.saveSala(sala));
      }
}
