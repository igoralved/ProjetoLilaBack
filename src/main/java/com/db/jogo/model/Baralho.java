package com.db.jogo.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Baralho {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@NonNull
	@Column(unique=true)
	private String codigo;

	@OneToMany
	private List<CartaDoJogo> cartasDoJogo;

	@OneToMany
	private List<CartaObjetivo> cartasDeObjetivo;

	@OneToMany
	private List<CartaInicio> cartasInicio;

	@NonNull
	@Column( name ="titulo", nullable = false)
	private String titulo;
	
	@Column( name ="descricao", nullable = false)
	private String descricao;

	public void adicionarCartadoJogo(CartaDoJogo cartaDoJogo){
		this.cartasDoJogo.add(cartaDoJogo);
	}

	public boolean removerCartaDoJogo(CartaDoJogo cartaDoJogo){
		return this.cartasDoJogo.remove(cartaDoJogo);
	}

	public void adicionarCartaDoObjetivo(CartaObjetivo cartaObjetivo){
		this.cartasDeObjetivo.add(cartaObjetivo);
	}

	public boolean removerCartaDoObjetivo(CartaObjetivo cartaDoObjetivo){
		return this.cartasDeObjetivo.remove(cartaDoObjetivo);
	}

	public void adicionarCartaDoInicio(CartaInicio cartaInicio){
		this.cartasInicio.add(cartaInicio);
	}

	public boolean removerCartaDoInicio(CartaInicio cartaInicio){
		return this.cartasInicio.remove(cartaInicio);
	}

}
