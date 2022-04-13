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

@Table(name="carta_objetivo")
public class CartaObjetivo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;


	@Column(name = "classificacao", length = 255, nullable = false)
	private String classificacao;

	@Column(name = "pontos", length = 10, nullable = false)
	private Integer pontos;

	@Column(name = "categoria", length = 80, nullable = false)
	private String categoria;

	@Column(name = "descricao", length = 255, nullable = false)
	private String descricao;

}
