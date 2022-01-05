package com.db.jogo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Baralho {
	
	@Id
	private  String id_codigo;
	
	@OneToMany(mappedBy = "baralho" )
	private List<CartaDoJogo> cartasDoJogo= new ArrayList<>();
	
	
	@Column( name ="titulo", nullable = false)
	private String titulo;
	
	@Column( name ="descricao", nullable = false)
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name="admin_id")
	private Admin admin;
	
	
	
	
}
