package com.db.jogo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
public class Baralho {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
	
	@OneToMany
	
	private List<CartaDoJogo> cartasDoJogo= new ArrayList<>();
	
	@OneToMany
	
	private List<CartaObjetivo> cartasObjetivo = new ArrayList<>();
	
	@OneToMany
	private List<CartaInicio> cartaInicio = new ArrayList<>();
	
	
	
	@NonNull
	@Column 
	private  String codigo;
	
	@NonNull
	@Column( name ="titulo")
	private String titulo;
	
	@NonNull
	@Column( name ="descricao")
	private String descricao;
	

	
	
	
	
}
