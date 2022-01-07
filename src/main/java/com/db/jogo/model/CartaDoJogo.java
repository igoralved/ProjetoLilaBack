package com.db.jogo.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class CartaDoJogo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private Integer pontos;
	private Integer numeroDeCoracoes;
	
	
	@ManyToOne
	@JoinColumn(name = "jogador_id")
	private Jogador jogador;
}
