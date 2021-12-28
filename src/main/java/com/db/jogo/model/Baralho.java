package com.db.jogo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="tb_baralho")
public class Baralho {
	
	@Id
	private String codigo;
	
	@Column
	private String titulo;
	
	@Column
	private String descricao;	
	
}
