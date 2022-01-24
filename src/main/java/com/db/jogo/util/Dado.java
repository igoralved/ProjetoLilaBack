package com.db.jogo.util;

import java.util.Random;

import com.db.jogo.model.CartaDoJogo;
import com.db.jogo.model.CartaObjetivo;
import com.db.jogo.model.Jogador;

public class Dado {

	public static void girarDado(CartaDoJogo cartaDoJogo, Jogador jogador) {

		Random ran = new Random();
		if (cartaDoJogo.getBonus()) {
			Integer dado = ran.nextInt(6) + 1;
			resultadoDoDado( cartaDoJogo.getTipo(), dado, jogador);
		}
	}

	private  static void discontaCoracao(Jogador jogador, Integer bonusCoracao){
		Integer totalCoracoes = validaQuantidadeCoracoes(jogador);
		switch(bonusCoracao){
			case -1:{
				if( jogador.getBonusCoracaoPeq() > 0){
					jogador.setBonusCoracaoPeq(jogador.getBonusCoracaoPeq() - 1);
				} else if(jogador.getBonusCoracaoGra() > 0){
					jogador.setBonusCoracaoGra(jogador.getBonusCoracaoGra() - 1);
				}
				break;
			}

			case 1:{
				if(totalCoracoes < 5){
					jogador.setBonusCoracaoPeq(jogador.getBonusCoracaoPeq() + 1);
				}
				break;
			}

			case 2:{
				if(totalCoracoes < 5){
					jogador.setBonusCoracaoGra(jogador.getBonusCoracaoGra() + 1);
				}
				break;
			}
		}
	}

	private static Jogador resultadoDoDado(String tipo, Integer dado, Jogador jogador) {
				// -1 = perde um coração bonus 
			   // 0 = igual não ganha e nem perde
			   // 1 = ganha um coração pequena 	
			   // 2 = ganha um coração grande 
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