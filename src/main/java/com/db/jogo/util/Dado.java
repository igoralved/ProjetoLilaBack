package com.db.jogo.util;

import java.util.Optional;
import java.util.Random;

import com.db.jogo.model.CartaDoJogo;
import com.db.jogo.model.Jogador;
import com.db.jogo.model.Sala;
import com.db.jogo.service.JogadorService;
import com.db.jogo.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;

public class Dado {
	private SalaService salaService;


	public Dado(SalaService salaService){
		this.salaService = salaService;
	}

	public Jogador  girarDado(CartaDoJogo cartaDoJogo, Jogador jogador, Sala sala) {
		Optional<Sala> salaAdicionarDado = this.salaService.findSalaByHash(sala.getHash());
		Jogador jogadorAtualizado = new Jogador();

		Random ran = new Random();
		if (cartaDoJogo.getBonus()) {
			Integer dado = ran.nextInt(6) + 1;
			jogadorAtualizado = resultadoDoDado( cartaDoJogo.getTipo(), sala.getDado(), jogador);

		}
		return jogadorAtualizado;

	}

	private   static void discontaCoracao(Jogador jogador, Integer bonusCoracao) {

		Integer totalCoracoes = validaQuantidadeCoracoes(jogador);
		switch (bonusCoracao) {
			case -1: {
				if (jogador.getBonusCoracaoPeq() > 0) {
					jogador.setBonusCoracaoPeq(jogador.getBonusCoracaoPeq() - 1);

				} else if (jogador.getBonusCoracaoGra() > 0) {
					jogador.setBonusCoracaoGra(jogador.getBonusCoracaoGra() - 1);
				}
				break;
			}

			case 1: {
				if (totalCoracoes < 5) {
					jogador.setBonusCoracaoPeq(jogador.getBonusCoracaoPeq() + 1);
				}
				break;
			}

			case 2: {
				if (totalCoracoes < 5) {
					jogador.setBonusCoracaoGra(jogador.getBonusCoracaoGra() + 1);
				}
				break;
			}
		}
	}

	public static Jogador resultadoDoDado(String tipo, Integer dado, Jogador jogador) {

		switch (tipo) {
			case "Ação": {
				 if(dado==1){
					 discontaCoracao(jogador, -1);
					return jogador;
				 }else if (dado<=3) {

			return jogador;
				 }else if (dado==4) {
					 discontaCoracao(jogador, 2);
				return jogador;
				}
				else if(dado>=5){
					 discontaCoracao(jogador, 1);
					return jogador;
				}
			}
			
			case "Informação":{
				if(dado<=4){
				return jogador;
				}
				else if(dado>=5){
					discontaCoracao(jogador, 1);
					return jogador;
				}
			}
		}
			return jogador;
	}
	 private static Integer validaQuantidadeCoracoes( Jogador jogador){
		return (jogador.getBonusCoracaoGra() + jogador.getCoracaoGra() + jogador.getBonusCoracaoPeq() + jogador.getCoracaoPeq() );
	}
}