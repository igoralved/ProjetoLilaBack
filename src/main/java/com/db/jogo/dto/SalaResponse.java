package com.db.jogo.dto;

import com.db.jogo.model.Jogador;
import com.db.jogo.model.Sala;

import lombok.Data;

@Data
public class SalaResponse {
    private Sala sala;
    private Jogador jogador;
}