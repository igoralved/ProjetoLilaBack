package com.db.jogo.model;

<<<<<<< HEAD
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
=======
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
>>>>>>> origin/US004modeldobaralho

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< HEAD
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity

public class Baralho {
	
	@Id
	private  String id_codigo;
		
	@ManyToOne
	@JoinColumn(name="admin_id_codigo")
	private Admin admin;

	@Column( name ="titulo", nullable = false)
	private String titulo;
	
	@Column( name ="descricao", nullable = false)
	private String descricao;
}
=======
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


>>>>>>> origin/US004modeldobaralho
