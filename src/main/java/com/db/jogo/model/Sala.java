package com.db.jogo.model;

import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name="sala")
public class Sala {

    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@Builder.Default
	private List<Jogador> jogadores = new ArrayList();

	@OneToOne
	
	private Baralho baralho;
	
	@NonNull
	@Column(name = "hash" , nullable =false )
	String hash;
    
	@NonNull
	@Column(name="dado" , length =1 , nullable = false)
	private Integer dado;
	
	@NotNull
	@Column(name="status")
	@Builder.Default
	private StatusEnum status = StatusEnum.NOVO;

	public String generateHash() {
		SecureRandom random = new SecureRandom();
		byte[] bytes = new byte[6];
		random.nextBytes(bytes);
		Encoder encoder = Base64.getUrlEncoder().withoutPadding();
		return encoder.encodeToString(bytes);
	}

	@NonNull
	public void adicionarJogador(Jogador jogador) {
		this.jogadores.add(jogador);
	}

	public boolean removerJogador(Jogador jogador) {
		return this.jogadores.remove(jogador);
	}

	public enum StatusEnum {
		NOVO, JOGANDO, FINALIZADO,AGUARDANDO , ULTIMA_RODADA
	}

	@NonNull
	public StatusEnum getStatus() {
		return this.status;
	}

	public void setStatus(@NonNull StatusEnum status) {
		this.status= status;
	}


}
