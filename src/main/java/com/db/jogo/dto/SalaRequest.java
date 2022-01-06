package com.db.jogo.dto;

import com.db.jogo.model.Jogador;
import lombok.Data;

import java.util.UUID;

@Data
public class SalaRequest {
    private String hash;
    private Jogador jogador;
}
