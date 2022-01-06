package com.db.jogo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
	
	@OneToMany
	@JoinColumn(name = "CartaDoJogo_id")
	@Builder.Default
	private ArrayList<CartaDoJogo> listaDeCartas = new ArrayList<>();
	@OneToMany
	@JoinColumn(name = "CartaDeObjetivo_id")
	@Builder.Default
	private ArrayList<CartaDeObjetivo> listaDeObjetivos = new ArrayList<>();
	
	@Column( name ="pontos")
	private Integer pontos;
	
	@Builder.Default
	@Column( name ="coracaoPeq", nullable = false)
	private Integer coracaoPeq = 2;
	
	@Column( name ="coracaoGra")
	private Integer coracaoGra;
	
	@Column( name ="bonusCoracaoPeq")
	private Integer bonusCoracaoPeq;
	
	@Column( name ="bonusCoracaoGra")
	private Integer bonusCoracaoGra;
	
	
	public void adicionaCarta(CartaDoJogo carta) {
		this.listaDeCartas.add(carta);
	}
	
	public void removeCarta(CartaDoJogo carta) {
		this.listaDeCartas.remove(carta);
	}
	
	public void adicionaObjetivo(CartaDeObjetivo carta) {
		this.listaDeObjetivos.add(carta);
	}
	
	public void removeObjetivo(CartaDeObjetivo carta) {
		this.listaDeObjetivos.remove(carta);
	}
	
	
	
}

