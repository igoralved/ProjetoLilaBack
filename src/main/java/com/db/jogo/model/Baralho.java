package com.db.jogo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
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
	@Column

	private String codigo;

	@OneToMany(cascade = CascadeType.ALL)
	@Builder.Default
	private List<CartaDoJogo> cartasDoJogo = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL)
	@Builder.Default
	private List<CartaObjetivo> cartasDeObjetivo = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL)
	@Builder.Default
	private List<CartaInicio> cartasInicio = new ArrayList<>();

	@NonNull
	@Column(name = "titulo", nullable = false)
	private String titulo;

	@Column(name = "descricao", nullable = false)
	private String descricao;

	public void adicionarCartadoJogo(CartaDoJogo cartaDoJogo) {
		this.cartasDoJogo.add(cartaDoJogo);
	}

	public boolean removerCartaDoJogo(CartaDoJogo cartaDoJogo) {
		return this.cartasDoJogo.remove(cartaDoJogo);
	}

	public void adicionarCartaDoObjetivo(CartaObjetivo cartaObjetivo) {
		this.cartasDeObjetivo.add(cartaObjetivo);
	}

	public boolean removerCartaDoObjetivo(CartaObjetivo cartaDoObjetivo) {
		return this.cartasDeObjetivo.remove(cartaDoObjetivo);
	}

	public void adicionarCartaDoInicio(CartaInicio cartaInicio) {
		this.cartasInicio.add(cartaInicio);
	}

	public boolean removerCartaDoInicio(CartaInicio cartaInicio) {
		return this.cartasInicio.remove(cartaInicio);
	}

}
