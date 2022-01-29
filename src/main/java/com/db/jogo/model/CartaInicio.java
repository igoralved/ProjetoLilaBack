package com.db.jogo.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name="carta_inicio")
public class CartaInicio {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column( name ="nome", length = 50, nullable = false )
	private String nome;
	
	@Column(name = "descricao", length = 255, nullable = false)
	private String descricao;
	
	
	
	
}

