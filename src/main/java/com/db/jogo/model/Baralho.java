package com.db.jogo.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

	@OneToMany(mappedBy = "baralho" )
	private List<CartaObjetivo> cartaObjetivo= new ArrayList<>();
		
	@ManyToOne
	@JoinColumn(name="admin_id_codigo")
	private Admin admin;

	@Column( name ="titulo", nullable = false)
	private String titulo;
	
	@Column( name ="descricao", nullable = false)
	private String descricao;
}
