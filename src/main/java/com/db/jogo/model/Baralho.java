package com.db.jogo.model;

import javax.persistence.*;

import lombok.*;

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

	public void adicionarCartaDoObjetivo(CartaObjetivo cartaObjetivo){
		this.cartaObjetivo.add(cartaObjetivo);
	}

	public boolean removerCartaDoObjetivo(CartaObjetivo cartaDoObjetivo){
		return this.cartaObjetivo.remove(cartaDoObjetivo);
	}
}
