package com.db.jogo.model;

import java.util.UUID;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class CartaObjetivo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column ( name = "classificacao", nullable = false)
    private String classificacao;

    @Column( name ="pontos", nullable = false)
    private Integer pontos;

    @Column( name ="categoria", nullable = false)
    private String categoria;

    @Column( name ="descricao", nullable = false)
    private String descricao;
}