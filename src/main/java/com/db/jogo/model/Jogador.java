package com.db.jogo.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Jogador {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String nome;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "jogador", fetch = FetchType.EAGER)
	@Builder.Default
	private Set<CartaDoJogo> listaDeCartas =  new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "jogador", fetch = FetchType.EAGER)
	@Builder.Default
	private  Set<CartaDeObjetivo> listaDeObjetivos = new HashSet<>();
	

	
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

