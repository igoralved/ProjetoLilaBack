package com.db.jogo.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.db.jogo.dto.SalaResponse;
import com.db.jogo.exception.CartaCompradaInvalidaException;
import com.db.jogo.exception.JogoInvalidoException;
import com.db.jogo.model.Baralho;
import com.db.jogo.model.CartaDoJogo;
import com.db.jogo.model.Jogador;
import com.db.jogo.model.Jogador.StatusEnumJogador;
import com.db.jogo.model.Sala;
import com.db.jogo.model.Sala.StatusEnum;
import com.db.jogo.util.Dado;
import com.db.jogo.util.RegrasDoJogo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WebSocketServiceImpl implements WebSocketService {

	private SimpMessagingTemplate template;
	private SalaService salaService;
	private BaralhoService baralhoService;
	private JogadorService jogadorService;
	private CartaDoJogoService cartaService;
	private int indexDoPoximoJogador;
	Jogador jogador;
	
	@Autowired
	private WebSocketServiceImpl(
			SalaService salaService,
			BaralhoService baralhoService,
			JogadorService jogadorService,
			SimpMessagingTemplate template,
			CartaDoJogoService cartaService) {
		this.salaService = salaService;
		this.baralhoService = baralhoService;
		this.jogadorService = jogadorService;
		this.template = template;
		this.cartaService = cartaService;
		this.jogador = new Jogador();
		this.indexDoPoximoJogador = 0;
	}

	public Optional<Sala> comprarCartaDoJogo(Sala salaFront) throws IllegalArgumentException {
		this.indexDoPoximoJogador = 0;
		Optional<Sala> salaParaAtualizar = this.salaService.findSalaByHash(salaFront.getHash());
		 //list<Jogador> jogadores = salaParaAtualizar.get().getJogadores();
		
		
		
		try {
			// verifico se a sala existe no banco
			if (salaParaAtualizar.isPresent()) {
				
				for (int index = 0; index < salaParaAtualizar.get().getJogadores().size(); index++ ) {
					
					this.jogador = salaParaAtualizar.get().getJogadores().get(index);
					
					// verifica qual o jogador da vez
					if (this.jogador.getStatus().equals(StatusEnumJogador.JOGANDO)) {
						

						CartaDoJogo cartaComprada = criarCartaDoJogo();

						// compara se a lista de carta do jogador no banco é menor que a lista que veio
						// do front para verificar se ele comrpou uma carta
						//colocar tam da lista carta do jogador == Tam Lista Carta Front -1 
						
							// captura qual carta o jogador comprou
							cartaComprada = procuraCartaComprada(salaFront);
							System.out.println("\nAQUI MOSTRA O carta jogando: " +cartaComprada.toString() +" ::\n");
							if (cartaComprada.getId()==null) {
								return salaParaAtualizar;
							}
						
						// fazer lógica do jogo e atualizar os status da sala

						// mapeia o jogador do banco de dados
						Optional<Jogador> jogadorParaAtualizar = this.jogadorService.findById(this.jogador.getId());
						

						// valida se o jogador pode comprar a carta
						if (RegrasDoJogo.validaCompraCarta(this.jogador, cartaComprada)) {
							// Seta os pontos da carta no jogador
							jogadorParaAtualizar.get()
									.setPontos(jogadorParaAtualizar.get().getPontos() + cartaComprada.getPontos());

							// Retira os corações da carta do jogador
							this.jogador = RegrasDoJogo.descontaCoracoes(this.jogador, cartaComprada);

							jogadorParaAtualizar.get().setCoracaoGra(this.jogador.getCoracaoGra());
							jogadorParaAtualizar.get().setCoracaoPeq(this.jogador.getCoracaoPeq());

							if (cartaComprada.getBonus()) {
								// jogador joga o dado
								Dado dado = new Dado();
								Jogador jogadorGirouDado = dado.girarDado(cartaComprada, jogadorParaAtualizar.get(),
										salaParaAtualizar.get());
								// jogador é atualizado conforme resultado do dado
								jogadorParaAtualizar.get().setBonusCoracaoGra(jogadorGirouDado.getBonusCoracaoGra());
								jogadorParaAtualizar.get().setBonusCoracaoPeq(jogadorGirouDado.getBonusCoracaoPeq());
							} else {
								salaParaAtualizar.get().setDado(0);
							}
							
							// Salva a carta no jogador
							Optional<CartaDoJogo> cartaParaAtualizarNoJogador = this.cartaService
									.findById(cartaComprada.getId());
							
			
							jogadorParaAtualizar.get().adicionaCarta(cartaParaAtualizarNoJogador.get());
							jogadorParaAtualizar.get().setStatus(StatusEnumJogador.ESPERANDO);

							this.jogadorService.saveJogador(jogadorParaAtualizar.get());

							
							
							
							if(index >= salaParaAtualizar.get().getJogadores().size()-1){
								this.indexDoPoximoJogador = 0;
							}else{								
								this.indexDoPoximoJogador = index+1;
							}
							
							salaParaAtualizar.get().getJogadores().set(index, jogadorParaAtualizar.get());
							salaParaAtualizar.get().getBaralho().getCartasDoJogo().remove(cartaComprada);
						}

						/*---*Fim da Lógica para Adicionar a Carta*----*/
					}
				}
				
				salaParaAtualizar.get().getJogadores().get(this.indexDoPoximoJogador).setStatus(StatusEnumJogador.JOGANDO);
				
				Optional<Sala> salaRetornoDoSaveNoBanco = Optional.ofNullable(
						this.salaService.saveSala(salaParaAtualizar.get()));

				// envia a sala para todos os jogadores conectados a sala
				if (salaRetornoDoSaveNoBanco.isPresent()) {
					this.template.convertAndSend(
							"URL/" + salaRetornoDoSaveNoBanco
									.get().getHash(),
							salaRetornoDoSaveNoBanco.get());
					// retorna sala que foi salva no banco
					return salaRetornoDoSaveNoBanco;

				}

			}

		} catch (Exception e) {
			throw new IllegalArgumentException("Jogada Não pode ser processada!! ", e);
		}

		return salaParaAtualizar;
	}

	public SalaResponse criarJogo(Jogador jogador) throws JogoInvalidoException {
		if (jogador.getNome().isEmpty()) {
			throw new JogoInvalidoException("dados incorretos");
		}
		Sala sala = new Sala();
		SalaResponse salaResp = new SalaResponse();
		Jogador savedJogador = jogadorService.saveJogador(criarPrimeiroJogador(jogador));
		Baralho baralho = baralhoService.findByCodigo("Clila").get();
		sala.setId(UUID.randomUUID());
		sala.setBaralho(baralho);
		sala.setJogadores(new ArrayList<>());
		sala.adicionarJogador(savedJogador);
		sala.setHash(sala.generateHash());
		sala.setDado(0);
		salaResp.setJogador(savedJogador);
		salaResp.setSala(salaService.saveSala(sala));
		return salaResp;
	}

	private CartaDoJogo procuraCartaComprada(Sala sala) throws CartaCompradaInvalidaException {

		CartaDoJogo cartaComprada = new CartaDoJogo();

		for (Jogador jogador : sala.getJogadores()) {
			if (jogador.getStatus() == StatusEnumJogador.JOGANDO) {
				try {
					Integer numCartaComprada = jogador.getCartasDoJogo().size() - 1;
					if (numCartaComprada >= 0) {
						cartaComprada = jogador.getCartasDoJogo().get(numCartaComprada);
					}
					return cartaComprada;
				} catch (Exception e) {
				throw new CartaCompradaInvalidaException("Carta Não encontrada na base de dados");
				}
			}
		}
		return cartaComprada;

	}

	public Jogador criarPrimeiroJogador(Jogador jogador) {
		jogador.setBonusCoracaoPeq(0);
		jogador.setBonusCoracaoGra(0);
		jogador.setCoracaoPeq(2);
		jogador.setCoracaoGra(0);
		jogador.setPontos(0);
		jogador.setIshost(true);
		jogador.setNome(jogador.getNome());
		jogador.setStatus(StatusEnumJogador.JOGANDO);
		return jogador;
	}

	public Jogador criarJogador(Jogador jogador) {
		jogador.setBonusCoracaoPeq(0);
		jogador.setBonusCoracaoGra(0);
		jogador.setCoracaoPeq(2);
		jogador.setCoracaoGra(0);
		jogador.setPontos(0);
		jogador.setIshost(false);
		jogador.setNome(jogador.getNome());
		jogador.setStatus(StatusEnumJogador.ESPERANDO);
		return jogador;
	}

	public CartaDoJogo criarCartaDoJogo() {
		CartaDoJogo carta = CartaDoJogo.builder()
				.bonus(false)
				.categoria("")
				.fonte("")
				.pontos(0)
				.valorCorGrande(0)
				.valorCorPequeno(0)
				.tipo("")
				.build();

		return carta;
	}

	public SalaResponse conectarJogo(Jogador jogador, String hash) throws JogoInvalidoException {
		if (jogador == null || hash == null) {
			throw new JogoInvalidoException("Parametros nulos");
		}
		Optional<Sala> sala = salaService.findSalaByHash(hash);

		SalaResponse salaResp = new SalaResponse();

		if (sala.isPresent()) {
			if (sala.get().getStatusEnum() == StatusEnum.FINALIZADO) {
				throw new JogoInvalidoException("Jogo ja foi finalizado");
			}
			Jogador savedJogador = jogadorService.saveJogador(criarJogador(jogador));
			sala.get().adicionarJogador(savedJogador);
			sala.get().setStatusEnum(StatusEnum.JOGANDO);

			salaResp.setJogador(savedJogador);
			salaResp.setSala(sala.get());
			salaService.saveSala(sala.get());
		}
		return salaResp;
	}

}