package com.db.jogo.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Baralho {
	
	
	@Id
	private UUID id;
	
	@Column 
	private  String codigo;
	
	@Column 
	private String titulo;
	
	@Column
	private String descricao;
	
}


