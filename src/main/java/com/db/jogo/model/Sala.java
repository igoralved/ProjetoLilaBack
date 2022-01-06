package com.db.jogo.model;

import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.persistence.*;

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
public class Sala {
    
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

    @Column(name = "hash",nullable = false)
    @NonNull
    String hash;

    @OneToMany
    private List<Jogador> jogadores;

    @OneToOne
    private Baralho baralho;

    private StatusEnum statusEnum = StatusEnum.NOVO;

    public String generateHash() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[6];
        random.nextBytes(bytes);
        Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        String hash = encoder.encodeToString(bytes);
        return hash;
    }

    public void adicionarJogador(Jogador jogador){
        this.jogadores.add(jogador);
    }

    public boolean removerJogador(Jogador jogador){
        return this.jogadores.remove(jogador);
    }

    public enum StatusEnum {
        NOVO,
        JOGANDO,
        FINALIZADO
    }

    public StatusEnum getStatusEnum() {
        return this.statusEnum;
    }

    public void setStatusEnum(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }
}
