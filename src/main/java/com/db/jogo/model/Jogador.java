package com.db.jogo.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String nome;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Builder.Default
    private Set<CartaDoJogo> listaDeCartas =  new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Builder.Default
    private  Set<CartaDeObjetivo> listaDeObjetivos = new HashSet<>();

    @NonNull
    @Column( name ="pontos")
    @Builder.Default
    private Integer pontos = 0;

    @NonNull
    @Builder.Default
    @Column( name ="coracaoPeq")
    private Integer coracaoPeq = 2;

    @NonNull
    @Builder.Default
    @Column( name ="coracaoGra")
    private Integer coracaoGra = 0;

    @NonNull
    @Builder.Default
    @Column( name ="bonusCoracaoPeq")
    private Integer bonusCoracaoPeq = 0;

    @NonNull
    @Builder.Default
    @Column( name ="bonusCoracaoGra")
    private Integer bonusCoracaoGra = 0;


    public void adicionaCarta(CartaDoJogo carta) {
        this.listaDeCartas.add(carta);
    }

    public void removeCarta(CartaDoJogo carta) {
        this.listaDeCartas.remove(carta);
    }

    public void adicionaObjetivo(CartaDeObjetivo carta) {
        this.listaDeObjetivos.add(carta);
    }

    public void removeObjetivo(CartaDeObjetivo carta) {
        this.listaDeObjetivos.remove(carta);
    }
}

