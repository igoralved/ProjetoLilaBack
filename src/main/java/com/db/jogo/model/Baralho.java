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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Table(name = "baralho")
public class Baralho {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;


	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "baralho_cartadojogo", joinColumns = {
			@JoinColumn(name = "baralho_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "cartadojogo_id", referencedColumnName = "id") })
	@Builder.Default
	private List<CartaDoJogo> cartasDoJogo = new ArrayList<>();


	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "baralho_cartaobjetivo", joinColumns = {
			@JoinColumn(name = "baralho_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "cartaobjetivo_id", referencedColumnName = "id") })
	@Builder.Default
	private List<CartaObjetivo> cartasObjetivo = new ArrayList<>();


	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "baralho_cartainicio", joinColumns = {
			@JoinColumn(name = "baralho_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "cartainicio_id", referencedColumnName = "id") })
	@Builder.Default
	private List<CartaInicio> cartasInicio = new ArrayList<>();

	@NonNull
	@Column(name = "codigo", length = 20, nullable = false, unique = true)
	private String codigo;


	@NonNull
	@Column(name = "titulo", length = 20, nullable = false)
	private String titulo;

	@NonNull
	@Column(name = "descricao", length = 255, nullable = false)
	private String descricao;

	public void adicionarCartadoJogo(CartaDoJogo cartaDoJogo) {
		this.cartasDoJogo.add(cartaDoJogo);
	}

	public boolean removerCartaDoJogo(CartaDoJogo cartaDoJogo) {
		return this.cartasDoJogo.remove(cartaDoJogo);
	}

	public void adicionarCartaDoObjetivo(CartaObjetivo cartaObjetivo) {
		this.cartasObjetivo.add(cartaObjetivo);
	}

	public boolean removerCartaDoObjetivo(CartaObjetivo cartaDoObjetivo) {
		return this.cartasObjetivo.remove(cartaDoObjetivo);
	}

	public void adicionarCartaDoInicio(CartaInicio cartaInicio) {
		this.cartasInicio.add(cartaInicio);
	}

	public boolean removerCartaDoInicio(CartaInicio cartaInicio) {
		return this.cartasInicio.remove(cartaInicio);
	}

}
