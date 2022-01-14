package com.db.jogo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

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

    @NonNull
    @Column ( name = "classificacao")
    private String classificacao;

    @NonNull
    @Column( name ="pontos")
    private Integer pontos;

    @NonNull
    @Column( name ="categoria")
    private String categoria;


    @NonNull
    @Column( name ="descricao")
    private String descricao;
}