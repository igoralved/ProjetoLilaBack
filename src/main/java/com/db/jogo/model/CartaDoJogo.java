package com.db.jogo.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class CartaDoJogo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private UUID id;

    @NonNull
    @Column(name = "tipo")
    private String tipo;

    @NonNull
    @Column(name = "categoria")
    private String categoria;

    @NonNull
    @Column(name = "bonus")
    private Boolean bonus;

    @NonNull
    @Column(name = "texto")
    private String texto;

    @NonNull
    @Column(name = "valorCorPequeno")
    private Integer valorCorPequeno;

    @NonNull
    @Column(name = "valorCorGrande")
    private Integer valorCorGrande;


    @NonNull
    @Column(name = "fonte")
    private String fonte;

    @NonNull
    @Column(name="pontos")
    private  Integer pontos;

}