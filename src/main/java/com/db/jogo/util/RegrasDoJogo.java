package com.db.jogo.util;

import com.db.jogo.model.CartaDoJogo;
import com.db.jogo.model.Jogador;

public class RegrasDoJogo {

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

}
