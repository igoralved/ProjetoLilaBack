package com.db.jogo.model;

import java.util.UUID;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name ="carta_do_jogo")
public class CartaDoJogo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private UUID id;

	@Column(name = "tipo",length=50 ,nullable = false)
	private String tipo;

	@Column(name = "categoria",length = 80, nullable = false)
	private String categoria;

	@Column(name = "bonus",length =10 , nullable = false)
	private Boolean bonus;

	@Column(name = "texto", length = 255,nullable = false)
	private String texto;

	@Column(name = "valorCorPequeno", length = 10 ,nullable = true)
	private Integer valorCorPequeno;

	@Column(name = "valorCorGrande", length = 10, nullable = true)
	private Integer valorCorGrande;

	@Column(name = "fonte",length = 80, nullable = false)
	private String fonte;

	@Column(name = "pontos", length = 20 ,nullable = false)
	private Integer pontos;

}
