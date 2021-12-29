package com.db.jogo.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class TipoCarta {
	@Id 
	private UUID id;
	
	@Column( name ="nome",nullable = false )
	private String nome;

	@Column( name ="bonus", nullable = false)
	private Boolean bonus;
}

