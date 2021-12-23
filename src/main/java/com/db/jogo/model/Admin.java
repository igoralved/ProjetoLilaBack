package com.db.jogo.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name="tb_admin")
public class Admin  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private UUID id;
	
	@Column
	private String senha;
}
