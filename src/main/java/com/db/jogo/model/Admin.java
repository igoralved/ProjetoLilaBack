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


import lombok.NonNull;



@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity

@Table(name="admin")
public class Admin {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
		
	@Column(name="senha", length =8 ,nullable =false)

	private String senha;
}
