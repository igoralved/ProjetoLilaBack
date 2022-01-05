package com.db.jogo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class CartaObjetivo{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column( name ="pontos", nullable = false)
    private Integer pontos;

    @Column( name ="categoria", nullable = false)
    private String categoria;

    @Column( name ="descricao", nullable = false)
    private String descricao;
}

