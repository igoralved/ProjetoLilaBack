package com.db.jogo.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.db.jogo.dto.SalaResponse;
import com.db.jogo.exception.CartaCompradaInvalidaException;
import com.db.jogo.exception.JogoInvalidoException;
import com.db.jogo.exception.JsonInvalidoException;
import com.db.jogo.model.Baralho;
import com.db.jogo.model.CartaDoJogo;
import com.db.jogo.model.Jogador;
import com.db.jogo.model.Jogador.StatusEnumJogador;
import com.db.jogo.model.Sala;
import com.db.jogo.model.Sala.StatusEnum;
import com.db.jogo.util.Dado;
import com.db.jogo.util.RegrasDoJogo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WebSocketServiceImpl implements WebSocketService {

	private SimpMessagingTemplate template;
	private SalaService salaService;
	private BaralhoService baralhoService;
	private JogadorService jogadorService;
	private CartaDoJogoService cartaService;
	private Integer indexDoProximoJogador;
	private Jogador jogador;
	private CartaDoJogo cartaComprada;

	@Autowired
	private WebSocketServiceImpl(SalaService salaService, BaralhoService baralhoService, JogadorService jogadorService,
			SimpMessagingTemplate template, CartaDoJogoService cartaService) {
		this.salaService = salaService;
		this.baralhoService = baralhoService;
		this.jogadorService = jogadorService;
		this.template = template;
		this.cartaService = cartaService;
		this.jogador = new Jogador();
		this.cartaComprada = new CartaDoJogo();
	}

	public Optional<Sala> comprarCartaDoJogo(Sala salaFront) throws IllegalArgumentException {

		Optional<Sala> salaParaAtualizar = this.salaService.findSalaByHash(salaFront.getHash());

		if (salaParaAtualizar.get().getStatus().equals(StatusEnum.FINALIZADO)) {
			salaParaAtualizar.get().setDado(0);
			return salaParaAtualizar;
		}

		try {
			// verifico se a sala existe no banco
			if (salaParaAtualizar.isPresent()) {
				// AQUI verificar status da sala, se for JOGANDO continua
				for (int index = 0; index < salaParaAtualizar.get().getJogadores().size(); index++) {

					this.jogador = salaParaAtualizar.get().getJogadores().get(index);
					Jogador jogadorStatusJogandoFront = procuraJogadorJogandoNoFront(salaFront);

					// verifica qual o jogador da vez
					if (this.jogador.getStatus().equals(StatusEnumJogador.JOGANDO)) {

						// Verifica se jogador comprou uma carta
						if (this.jogador.getCartasDoJogo().size() >= jogadorStatusJogandoFront.getCartasDoJogo()
								.size()) {
							this.sendSala(salaParaAtualizar.get());
							return salaParaAtualizar;
						}
						/*---Inicio da Lógica de Comprar Carta----*/

						// captura qual carta o jogador comprou
						this.cartaComprada = procuraCartaComprada(salaFront);

						// verifica se a carta não é nula ou já esta na mão do jogador
						if (this.cartaComprada.getId() == null) {
							this.sendSala(salaParaAtualizar.get());
							return salaParaAtualizar;
						}
						if (this.jogador.getCartasDoJogo().contains(cartaComprada)) {
							this.sendSala(salaParaAtualizar.get());
							return salaParaAtualizar;
						}

						// fazer lógica do jogo e atualizar os status da sala

						// mapeia o jogador do banco de dados
						Optional<Jogador> jogadorParaAtualizar = this.jogadorService.findById(this.jogador.getId());

						// valida se o jogador pode comprar a carta
						if (RegrasDoJogo.validaCompraCarta(jogadorParaAtualizar.get(), cartaComprada)) {
							// Seta os pontos da carta no jogador
							jogadorParaAtualizar.get()
									.setPontos(jogadorParaAtualizar.get().getPontos() + cartaComprada.getPontos());

							// Seta estado da sala para ultima rodada
							if (jogadorParaAtualizar.get().getPontos() >= 8) {
								salaParaAtualizar.get().setStatus(StatusEnum.ULTIMA_RODADA);
							}
							// Retira os corações da carta do jogador
							this.jogador = RegrasDoJogo.descontaCoracoes(this.jogador, cartaComprada);

							jogadorParaAtualizar.get().setCoracaoGra(this.jogador.getCoracaoGra());
							jogadorParaAtualizar.get().setCoracaoPeq(this.jogador.getCoracaoPeq());

							if (cartaComprada.getBonus()) {
								// jogador joga o dado
								Dado dado = new Dado();
								Jogador jogadorGirouDado = dado.girarDado(this.cartaComprada,
										jogadorParaAtualizar.get(),
										salaParaAtualizar.get());
								// jogador é atualizado conforme resultado do dado
								jogadorParaAtualizar.get().setBonusCoracaoGra(jogadorGirouDado.getBonusCoracaoGra());
								jogadorParaAtualizar.get().setBonusCoracaoPeq(jogadorGirouDado.getBonusCoracaoPeq());
							} else {
								salaParaAtualizar.get().setDado(0);
							}

							// Salva a carta no jogador
							Optional<CartaDoJogo> cartaParaAtualizarNoJogador = this.cartaService
									.findById(this.cartaComprada.getId());

							jogadorParaAtualizar.get().adicionaCarta(cartaParaAtualizarNoJogador.get());
							jogadorParaAtualizar.get().setStatus(StatusEnumJogador.ESPERANDO);

							this.jogadorService.saveJogador(jogadorParaAtualizar.get());

							if (jogadorParaAtualizar.get().getPosicao() >= salaParaAtualizar.get().getJogadores()
									.size()) {
								this.indexDoProximoJogador = 1;
							} else {
								this.indexDoProximoJogador = jogadorParaAtualizar.get().getPosicao() + 1;
							}

							salaParaAtualizar.get().getBaralho().getCartasDoJogo()
									.remove(cartaParaAtualizarNoJogador.get());
							
							// Verifica se o próximo jogador é o que iniciou a partida e encerra a partida
							if (salaParaAtualizar.get().getStatus().equals(StatusEnum.ULTIMA_RODADA)) {
								
								for (Jogador jog : salaParaAtualizar.get().getJogadores()) {
									if (jog.getPosicao() == this.indexDoProximoJogador && jog.getIshost()) {
										salaParaAtualizar.get().setStatus(StatusEnum.FINALIZADO);
										break;
									}
								}
							}
						}
						/*---*Fim da Lógica para Adicionar a Carta*----*/
					}
				}

				for (Jogador jog : salaParaAtualizar.get().getJogadores()) {
					if (jog.getPosicao() == this.indexDoProximoJogador) {
						jog.setStatus(StatusEnumJogador.JOGANDO);
					}
				}

				// TODO: Colocar método para destruir as cartas restantes do jogo

				Optional<Sala> salaRetornoDoSaveNoBanco = Optional.ofNullable(
						this.salaService.saveSala(salaParaAtualizar.get()));

				// envia a sala para todos os jogadores conectados a sala
				if (salaRetornoDoSaveNoBanco.isPresent()) {
					this.template.convertAndSend("/gameplay/game-update/" + salaRetornoDoSaveNoBanco.get().getHash(),
							salaRetornoDoSaveNoBanco.get());

					return salaRetornoDoSaveNoBanco;
				}
			}

		} catch (Exception e) {
			throw new IllegalArgumentException("Jogada Não pode ser processada!! ", e);
		}

		return salaParaAtualizar;
	}

	public Jogador procuraJogadorJogandoNoFront(Sala sala) {
		for (Jogador jogadorFront : sala.getJogadores()) {
			if (jogadorFront.getStatus().equals(StatusEnumJogador.JOGANDO)) {
				return jogadorFront;
			}
		}
		return null;
	}

	public SalaResponse criarJogo(Jogador jogador) throws JogoInvalidoException {
		if (jogador.getNome().isEmpty()) {
			throw new JogoInvalidoException("dados incorretos");
		}
		Sala sala = new Sala();
		SalaResponse salaResp = new SalaResponse();
		Jogador savedJogador = jogadorService.saveJogador(criarPrimeiroJogador(jogador));
		Baralho baralho = criarBaralho();
		Collections.shuffle(baralho.getCartasDoJogo());
		Collections.shuffle(baralho.getCartasInicio());
		Collections.shuffle(baralho.getCartasObjetivo());
		sala.setId(UUID.randomUUID());
		sala.setJogadores(new ArrayList<>());
		sala.adicionarJogador(savedJogador);
		sala.setHash(sala.generateHash());
		baralho.setCodigo(sala.getHash());
		sala.setBaralho(baralho);
		sala.setDado(0);
		salaResp.setJogador(savedJogador);
		sala.setStatus(StatusEnum.AGUARDANDO);
		salaResp.setSala(salaService.saveSala(sala));
		return salaResp;
	}

	private CartaDoJogo procuraCartaComprada(Sala sala) throws CartaCompradaInvalidaException {

		CartaDoJogo carta = new CartaDoJogo();

		for (Jogador jogador : sala.getJogadores()) {
			if (jogador.getStatus() == StatusEnumJogador.JOGANDO) {
				try {
					Integer posicaoCartaComprada = jogador.getCartasDoJogo().size() - 1;
					if (posicaoCartaComprada >= 0) {
						carta = jogador.getCartasDoJogo().get(posicaoCartaComprada);
					}
					return carta;
				} catch (Exception e) {
					throw new CartaCompradaInvalidaException("Carta Não encontrada na base de dados");
				}
			}
		}
		return carta;
	}

	private Baralho criarBaralho() {
		Baralho baralho = baralhoService.findByCodigo("Clila").get();
		Baralho baralhoCopy = new Baralho();
		baralhoCopy.setCartasDoJogo(baralho.getCartasDoJogo());
		baralhoCopy.setCartasInicio(baralho.getCartasInicio());
		baralhoCopy.setCartasObjetivo(baralho.getCartasObjetivo());
		baralhoCopy.setCodigo("Clila");
		baralhoCopy.setDescricao(baralho.getDescricao());
		baralhoCopy.setTitulo(baralho.getTitulo());
		baralhoCopy.setId(UUID.randomUUID());
		System.out.println(baralhoCopy);
		return baralhoService.saveBaralho(baralhoCopy);
	}

	public Jogador criarPrimeiroJogador(Jogador jogador) {
		jogador.setBonusCoracaoPeq(0);
		jogador.setBonusCoracaoGra(0);
		jogador.setCoracaoPeq(2);
		jogador.setCoracaoGra(0);
		jogador.setPontos(0);
		jogador.setPosicao(1);
		jogador.setIshost(true);
		jogador.setNome(jogador.getNome());
		jogador.setStatus(StatusEnumJogador.JOGANDO);
		return jogador;
	}

	public Jogador criarJogador(Jogador jogador, Integer num) {
		jogador.setBonusCoracaoPeq(0);
		jogador.setBonusCoracaoGra(0);
		jogador.setCoracaoPeq(2);
		jogador.setCoracaoGra(0);
		jogador.setPontos(0);
		jogador.setPosicao(num);
		jogador.setIshost(false);
		jogador.setNome(jogador.getNome());
		jogador.setStatus(StatusEnumJogador.ESPERANDO);
		return jogador;
	}

	public CartaDoJogo criarCartaDoJogo() {
		CartaDoJogo carta = CartaDoJogo.builder().bonus(false).categoria("").fonte("").pontos(0).valorCorGrande(0)
				.valorCorPequeno(0).tipo("").build();
		return carta;
	}

	public Optional<Sala> compraCoracoesPequenos(Sala salaFront) throws IllegalArgumentException {

		Optional<Sala> salaParaAtualizar = this.salaService.findSalaByHash(salaFront.getHash());

		try {

			if (salaParaAtualizar.isPresent()) {

				for (int index = 0; index < salaParaAtualizar.get().getJogadores().size(); index++) {

					this.jogador = salaParaAtualizar.get().getJogadores().get(index);
					
					if (this.jogador.getStatus().equals(StatusEnumJogador.JOGANDO)) {
						
						Optional<Jogador> jogadorParaAtualizar = this.jogadorService.findById(this.jogador.getId());
						RegrasDoJogo.adicionaCoracoesPequenos(jogador);

						jogadorParaAtualizar.get().setCoracaoPeq(this.jogador.getCoracaoPeq());

						jogadorParaAtualizar.get().setStatus(StatusEnumJogador.ESPERANDO);

						if (jogadorParaAtualizar.get().getPosicao() >= salaParaAtualizar.get().getJogadores()
								.size()) {
							this.indexDoProximoJogador = 1;
						} else {
							this.indexDoProximoJogador = jogadorParaAtualizar.get().getPosicao() + 1;
						}
						
						this.jogadorService.saveJogador(jogadorParaAtualizar.get());

						salaParaAtualizar.get().getJogadores().set(index, jogadorParaAtualizar.get());
						
					}


					if (salaParaAtualizar.get().getStatus().equals(StatusEnum.ULTIMA_RODADA)) {
						
						for (Jogador jog : salaParaAtualizar.get().getJogadores()) {
							if (jog.getPosicao() == this.indexDoProximoJogador && jog.getIshost()) {
								salaParaAtualizar.get().setStatus(StatusEnum.FINALIZADO);
								break;
							}
						}
					}
				}
			}
			
			for (Jogador jog : salaParaAtualizar.get().getJogadores()) {
				if (jog.getPosicao() == this.indexDoProximoJogador) {
					jog.setStatus(StatusEnumJogador.JOGANDO);
				}
			}
			
			salaParaAtualizar.get().setDado(0);

			Optional<Sala> salaRetornoDoSaveNoBanco = Optional
					.ofNullable(this.salaService.saveSala(salaParaAtualizar.get()));

			if (salaRetornoDoSaveNoBanco.isPresent()) {
				this.template.convertAndSend("/gameplay/game-update/" + salaRetornoDoSaveNoBanco.get().getHash(),
						salaRetornoDoSaveNoBanco.get());
				return salaRetornoDoSaveNoBanco;

			}
		} catch (Exception e) {
			throw new IllegalArgumentException("Coração não pode ser comprado!! ", e);
		}

		return salaParaAtualizar;
	}

	// CORAÇÃO GRANDE
	public Optional<Sala> compraCoracoesGrandes(Sala salaFront) throws IllegalArgumentException {
		Optional<Sala> salaParaAtualizar = this.salaService.findSalaByHash(salaFront.getHash());

		try {

			if (salaParaAtualizar.isPresent()) {

				for (int index = 0; index < salaParaAtualizar.get().getJogadores().size(); index++) {

					this.jogador = salaParaAtualizar.get().getJogadores().get(index);
					
					if (this.jogador.getStatus().equals(StatusEnumJogador.JOGANDO)) {
						
						Optional<Jogador> jogadorParaAtualizar = this.jogadorService.findById(this.jogador.getId());
						RegrasDoJogo.adicionaCoracoesGrandes(jogador);

						jogadorParaAtualizar.get().setCoracaoPeq(this.jogador.getCoracaoPeq());

						jogadorParaAtualizar.get().setStatus(StatusEnumJogador.ESPERANDO);

						if (jogadorParaAtualizar.get().getPosicao() >= salaParaAtualizar.get().getJogadores()
								.size()) {
							this.indexDoProximoJogador = 1;
						} else {
							this.indexDoProximoJogador = jogadorParaAtualizar.get().getPosicao() + 1;
						}
						
						this.jogadorService.saveJogador(jogadorParaAtualizar.get());

						salaParaAtualizar.get().getJogadores().set(index, jogadorParaAtualizar.get());
						
					}


					if (salaParaAtualizar.get().getStatus().equals(StatusEnum.ULTIMA_RODADA)) {
						
						for (Jogador jog : salaParaAtualizar.get().getJogadores()) {
							if (jog.getPosicao() == this.indexDoProximoJogador && jog.getIshost()) {
								salaParaAtualizar.get().setStatus(StatusEnum.FINALIZADO);
								break;
							}
						}
					}
				}
			}
			
			for (Jogador jog : salaParaAtualizar.get().getJogadores()) {
				if (jog.getPosicao() == this.indexDoProximoJogador) {
					jog.setStatus(StatusEnumJogador.JOGANDO);
				}
			}
			
			salaParaAtualizar.get().setDado(0);

			Optional<Sala> salaRetornoDoSaveNoBanco = Optional
					.ofNullable(this.salaService.saveSala(salaParaAtualizar.get()));

			if (salaRetornoDoSaveNoBanco.isPresent()) {
				this.template.convertAndSend("/gameplay/game-update/" + salaRetornoDoSaveNoBanco.get().getHash(),
						salaRetornoDoSaveNoBanco.get());
				return salaRetornoDoSaveNoBanco;

			}
		} catch (Exception e) {
			throw new IllegalArgumentException("Coração não pode ser comprado!! ", e);
		}

		return salaParaAtualizar;
	
	}

	public SalaResponse conectarJogo(Jogador jogador, String hash) throws JogoInvalidoException {
		if (jogador == null || hash == null) {
			throw new JogoInvalidoException("Parametros nulos");
		}
		Optional<Sala> sala = salaService.findSalaByHash(hash);

		SalaResponse salaResp = new SalaResponse();

		if (sala.isPresent()) {
			if (sala.get().getStatus() == StatusEnum.FINALIZADO) {
				throw new JogoInvalidoException("Jogo ja foi finalizado");
			}
			Jogador savedJogador = jogadorService
					.saveJogador(criarJogador(jogador, sala.get().getJogadores().size() + 1));
			sala.get().adicionarJogador(savedJogador);

			salaResp.setJogador(savedJogador);
			salaResp.setSala(this.salaService.saveSala(sala.get()));
			salaService.saveSala(sala.get());
		}
		return salaResp;
	}

	public Integer getQuantidadeJogadores(String hash) {

		Integer numero = salaService.totalJogadores(hash);
		String url = "/gameplay/" + hash;
		template.convertAndSend(url, numero);

		return numero;
	}

	public void sendSala(Sala sala) throws JsonInvalidoException {
		ObjectMapper mapper = new ObjectMapper();
		String salaAsJSON;
		String url = "/gameplay/game-update/" + sala.getHash();
		try {
			salaAsJSON = mapper.writeValueAsString(sala);
		} catch (JsonProcessingException e) {
			throw new JsonInvalidoException("Não foi possível construir o JSON da sala.");
		}

		template.convertAndSend(url, salaAsJSON);
	}

	public Optional<Sala> iniciarPartida(Sala sala) throws JogoInvalidoException {
		Optional<Sala> salaParaAtualizar = this.salaService.findSalaByHash(sala.getHash());
		try {
			if (salaParaAtualizar.isPresent()) {

				salaParaAtualizar.get().setStatus(StatusEnum.JOGANDO);
				this.salaService.saveSala(salaParaAtualizar.get());

				return salaParaAtualizar;
			}
		} catch (Exception e) {
			throw new JogoInvalidoException("Sala não encontrada");
		}
		return salaParaAtualizar;
	}
}