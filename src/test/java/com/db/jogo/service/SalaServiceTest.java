package com.db.jogo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.db.jogo.model.Sala;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SalaServiceTest {
    @Autowired
    SalaService salaService;
    Sala sala = Sala.builder().hash("iuervnijr0f").build();

    @Test
    void testFindSalaByHash() throws Exception {
        assertEquals(true, salaService.findSalaByHash("iuervnijr0f"));
    }

    // @Test
    // void testSaveSala() throws Exception {
        
    //     assertEquals(true, salaService.saveSala());

    // }
}
