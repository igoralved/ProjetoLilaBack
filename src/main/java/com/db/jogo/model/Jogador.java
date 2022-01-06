package com.db.jogo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Jogador {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String nome;
	
	//@OneToMany
	//@JoinColumn(name = "CartaDoJogo_id")
	//private ArrayList<CartaDoJogo> listaDeCartas;
	private Integer pontos;
	private Integer coracaoPeq;
	private Integer coracaoGra;
	private Integer bonusCoracaoPeq;
	private Integer bonusCoracaoGra;
}

