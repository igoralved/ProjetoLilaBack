package com.db.jogo.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Baralho {
	
	@Id
	private  String id_codigo;

	@OneToMany
	private List<CartaDoJogo> cartasDoJogo= new ArrayList<>();

	@OneToMany
	private List<CartaDeObjetivo> cartasDeObjetivo = new ArrayList<>();

	@OneToMany
	private List<CartaInicio> cartasInicio = new ArrayList<>();

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

	public void adicionarCartaDoObjetivo(CartaDeObjetivo cartaDeObjetivo){
		this.cartasDeObjetivo.add(cartaDeObjetivo);
	}

	public boolean removerCartaDoObjetivo(CartaDeObjetivo cartaDoObjetivo){
		return this.cartasDeObjetivo.remove(cartaDoObjetivo);
	}

	public void adicionarCartaDoInicio(CartaInicio cartaInicio){
		this.cartasInicio.add(cartaInicio);
	}

	public boolean removerCartaDoInicio(CartaInicio cartaInicio){
		return this.cartasInicio.remove(cartaInicio);
	}

}
