package com.db.jogo.util;

import com.db.jogo.model.CartaDoJogo;
import com.db.jogo.model.Jogador;
import com.db.jogo.model.Sala;
import com.db.jogo.model.Sala.StatusEnum;


public abstract class RegrasDoJogo {
	
	public static void verificaJogadorSeTemOitoPontos(Jogador jogador, Sala sala) {
        if(jogador.getPontos() >= 8) {
            sala.setStatus(StatusEnum.ULTIMA_RODADA);
        }
    }
	

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
	
	public static Jogador adicionaCoracoesPequenos (Jogador jogador) {
	
		 int coracaoPequenos = 2;
		 
		 if(Dado.quantidaDeCoracoes(jogador) < 4) {
			 coracaoPequenos += Dado.quantidaDeCoracoes(jogador);
			 						 
		 }
			
		
		return jogador;
		
	}
	
	// metodo para coracoes grande 
	
	public static boolean validaCompraCartaObjetivo(Jogador jogador) {

		if (jogador.getBonusCoracaoPeq() + jogador.getCoracaoPeq() + jogador.getBonusCoracaoGra() + jogador.getCoracaoGra() < 1) {
				return false;
			}

		return true;

	}
	

}