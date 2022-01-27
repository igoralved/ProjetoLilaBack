package com.db.jogo.util;

import com.db.jogo.model.CartaDoJogo;
import com.db.jogo.model.Jogador;
import com.db.jogo.model.Sala;
import com.db.jogo.model.Sala.StatusEnum;

public abstract class RegrasDoJogo {

	public static boolean validaCompraCarta(Jogador jogador, CartaDoJogo carta) {

		if (carta.getValorCorPequeno() >= 0) {
			if (jogador.getBonusCoracaoPeq() + jogador.getCoracaoPeq() < carta.getValorCorPequeno()) {
				return false;
			}

		}

		if (carta.getValorCorGrande() >= 0) {
			if (jogador.getBonusCoracaoGra() + jogador.getCoracaoGra() < carta.getValorCorGrande()) {
				return false;
			}

		}

		return true;

	}
	
	
	public static boolean validaCompraCartaObjetivo(Jogador jogador) {

		if (jogador.getBonusCoracaoPeq() + jogador.getCoracaoPeq() + jogador.getBonusCoracaoGra() + jogador.getCoracaoGra() < 1) {
				return false;
			}

		return true;

	}

	public static void verificaJogadorSeTemOitoPontos(Jogador jogador, Sala sala) {
		if(jogador.getPontos() >= 8) {
			sala.setStatusEnum(StatusEnum.ULTIMA_RODADA); 
		}
	}
	
}
