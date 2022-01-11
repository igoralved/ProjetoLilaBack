package com.db.jogo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class CartaInicio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name ="nome", nullable = false)
    private String nome;

    @Column(name = "descricao", nullable = false)
    private String descricao;

   @ManyToOne
    @JoinColumn(name = "baralho_id_codigo")
    private Baralho baralho;
}
