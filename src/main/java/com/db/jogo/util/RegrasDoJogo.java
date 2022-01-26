package com.db.jogo.util;

import com.db.jogo.model.CartaDoJogo;
import com.db.jogo.model.Jogador;


public abstract class RegrasDoJogo {
	

    public static Jogador descontaCoracoes(Jogador jogador, CartaDoJogo carta) {
    	
    	int numCoracoesGraDaCarta = carta.getValorCorGrande();
		int numCoracoesPeqDaCarta = carta.getValorCorPequeno();
		
		if(jogador.getBonusCoracaoPeq() > 0) {
			numCoracoesPeqDaCarta -= jogador.getBonusCoracaoPeq();
		}
		if(jogador.getBonusCoracaoGra() > 0) {
			numCoracoesGraDaCarta -= jogador.getBonusCoracaoGra();
		}
		if(numCoracoesGraDaCarta > 0) {
			jogador.setCoracaoGra(jogador.getCoracaoGra() - numCoracoesGraDaCarta);
		}
		if(numCoracoesPeqDaCarta > 0) {
			jogador.setCoracaoPeq(jogador.getCoracaoPeq() - numCoracoesPeqDaCarta);
		}
    	return jogador ;
    }
    
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
	

}