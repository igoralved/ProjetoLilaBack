package com.db.jogo.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class CartaInicio {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@NonNull
	@Column( name ="nome" )
	private String nome;
	
	@NonNull
	@Column(name = "descricao")
	private String descricao;
	
	
	
	
}
