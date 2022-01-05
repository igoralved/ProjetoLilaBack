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
public class Sala {
    
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

    @Column(name = "hash",nullable = false)
    String hash;

    //List <Player> player;
    @Column(name = "coracaoPequeno",nullable = false)
    Integer coracaoPequeno;

    @Column(name = "coracaoGrande",nullable = false)
    Integer coracaoGrande;

    @Column(name = "bonusCoracaoPequeno",nullable = false)
    Integer bonusCoracaoPequeno;

    @Column(name = "bonusCoracaoPequeno",nullable = false)
    Integer bonusCoracaoGrande;

    @OneToOne
    Baralho baralho;

    
}
