package com.db.jogo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class CartaObjetivo{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name="baralho_id_codigo")
    private Baralho baralho;

    @Column ( name = "classificacao", nullable = false)
    private String classificacao;

    @Column( name ="pontos", nullable = false)
    private Integer pontos;

    @Column( name ="categoria", nullable = false)
    private String categoria;

    @Column( name ="descricao", nullable = false)
    private String descricao;
}

