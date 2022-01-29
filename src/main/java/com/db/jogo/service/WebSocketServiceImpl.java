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
	private int indexDoProximoJogador;
	private Jogador jogador;

	@Autowired
	private WebSocketServiceImpl(SalaService salaService, BaralhoService baralhoService, JogadorService jogadorService,
			SimpMessagingTemplate template, CartaDoJogoService cartaService) {
		this.salaService = salaService;
		this.baralhoService = baralhoService;
		this.jogadorService = jogadorService;
		this.template = template;
		this.cartaService = cartaService;
		this.jogador = new Jogador();
		this.indexDoProximoJogador = 0;
	}

	public Optional<Sala> comprarCartaDoJogo(Sala salaFront) throws IllegalArgumentException {

		Optional<Sala> salaParaAtualizar = this.salaService.findSalaByHash(salaFront.getHash());
		
		if(salaParaAtualizar.get().getStatus().equals(StatusEnum.FINALIZADO)) {
			salaParaAtualizar.get().setDado(0);
			return Optional.ofNullable(this.salaService.saveSala(salaParaAtualizar.get()));
		}
		
		try {
			// verifico se a sala existe no banco
			if (salaParaAtualizar.isPresent()) {
				// AQUI verificar status da sala, se for JOGANDO continua
				for (int index = 0; index < salaParaAtualizar.get().getJogadores().size(); index++) {

					this.jogador = salaParaAtualizar.get().getJogadores().get(index);

					// verifica qual o jogador da vez
					if (this.jogador.getStatus().equals(StatusEnumJogador.JOGANDO)) {

						
		/*---Inicio da Lógica de Comprar Carta----*/
						CartaDoJogo cartaComprada = criarCartaDoJogo();
						
						//Veririfica se jogador comprou alguma carta
						if(this.jogador.getCartasDoJogo().size() ==	salaFront
																	.getJogadores()
																	.get(index).getCartasDoJogo().size()) {
							
							return salaParaAtualizar;
							
						}else if(this.jogador.getCartasDoJogo().isEmpty()){
							if(!salaParaAtualizar.get()
									.getBaralho()
									.getCartasDoJogo()
									.contains(salaFront
											.getJogadores()
											.get(index)
											.getCartasDoJogo()
												.get(0))) {
								
								return salaParaAtualizar;
							}
							
						}else if(!salaParaAtualizar.get()
								.getBaralho()
								.getCartasDoJogo()
								.contains(salaFront
										.getJogadores()
										.get(index)
										.getCartasDoJogo()
											.get(this.jogador.getCartasDoJogo().size()))) {
							
							return salaParaAtualizar;
						}
						// captura qual carta o jogador comprou
						cartaComprada = procuraCartaComprada(salaFront);

						if (cartaComprada.getId() == null) {
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

							if (index >= salaParaAtualizar.get().getJogadores().size() - 1) {
								this.indexDoProximoJogador = 0;
							} else {
								this.indexDoProximoJogador = index + 1;
							}

							salaParaAtualizar.get().getJogadores().set(index, jogadorParaAtualizar.get());
							salaParaAtualizar.get().getBaralho().getCartasDoJogo().remove(cartaComprada);
						}

						RegrasDoJogo.verificaJogadorSeTemOitoPontos(this.jogador,salaParaAtualizar.get());	
						
						/*---*Fim da Lógica para Adicionar a Carta*----*/
					}
				}
				
				salaParaAtualizar.get().getJogadores().get(this.indexDoProximoJogador).setStatus(StatusEnumJogador.JOGANDO);
				
				if(salaParaAtualizar.get().getStatus().equals(StatusEnum.ULTIMA_JOGADA)) {
					if(salaParaAtualizar.get().getJogadores().get(this.indexDoProximoJogador).getIshost()) {
						salaParaAtualizar.get().setStatus(StatusEnum.FINALIZADO);
					}
				}
				Optional<Sala> salaRetornoDoSaveNoBanco = Optional.ofNullable(
						this.salaService.saveSala(salaParaAtualizar.get()));

				// envia a sala para todos os jogadores conectados a sala
				if (salaRetornoDoSaveNoBanco.isPresent()) {
					sendSala(salaRetornoDoSaveNoBanco.get());
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
		sala.setStatus(StatusEnum.AGUARDANDO);
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
		jogador.setCoracaoPeq(3);
		jogador.setCoracaoGra(2);
		jogador.setPontos(0);
		jogador.setIshost(true);
		jogador.setNome(jogador.getNome());
		jogador.setStatus(StatusEnumJogador.JOGANDO);
		return jogador;
	}

	public Jogador criarJogador(Jogador jogador) {
		jogador.setBonusCoracaoPeq(0);
		jogador.setBonusCoracaoGra(0);
		jogador.setCoracaoPeq(3);
		jogador.setCoracaoGra(2);
		jogador.setPontos(0);
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

						RegrasDoJogo.adicionaCoracoesPequenos(jogador);

					}

					Optional<Jogador> jogadorParaAtualizar = this.jogadorService.findById(this.jogador.getId());

					jogadorParaAtualizar.get().setCoracaoPeq(this.jogador.getCoracaoPeq());

					jogadorParaAtualizar.get().setStatus(StatusEnumJogador.ESPERANDO);

					this.jogadorService.saveJogador(jogadorParaAtualizar.get());

					if (index >= salaParaAtualizar.get().getJogadores().size() - 1) {
						this.indexDoProximoJogador = 0;
					} else {
						this.indexDoProximoJogador = index + 1;
					}

					salaParaAtualizar.get().getJogadores().set(index, jogadorParaAtualizar.get());

				}
			}
			salaParaAtualizar.get().getJogadores().get(this.indexDoProximoJogador).setStatus(StatusEnumJogador.JOGANDO);
			
			if(salaParaAtualizar.get().getStatus().equals(StatusEnum.ULTIMA_JOGADA)) {
				if(salaParaAtualizar.get().getJogadores().get(this.indexDoProximoJogador).getIshost()) {
					salaParaAtualizar.get().setStatus(StatusEnum.FINALIZADO);
				}
			}

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

						RegrasDoJogo.adicionaCoracoesGrandes(jogador);

					}

					Optional<Jogador> jogadorParaAtualizar = this.jogadorService.findById(this.jogador.getId());

					jogadorParaAtualizar.get().setCoracaoGra(this.jogador.getCoracaoGra());

					jogadorParaAtualizar.get().setStatus(StatusEnumJogador.ESPERANDO);

					this.jogadorService.saveJogador(jogadorParaAtualizar.get());

					if (index >= salaParaAtualizar.get().getJogadores().size() - 1) {
						this.indexDoProximoJogador = 0;
					} else {
						this.indexDoProximoJogador = index + 1;
					}

					salaParaAtualizar.get().getJogadores().set(index, jogadorParaAtualizar.get());

				}
			}

			salaParaAtualizar.get().getJogadores().get(this.indexDoProximoJogador).setStatus(StatusEnumJogador.JOGANDO);

			if (salaParaAtualizar.get().getStatus().equals(StatusEnum.ULTIMA_JOGADA)) {

				if (salaParaAtualizar.get().getJogadores().get(this.indexDoProximoJogador).getIshost()) {
					salaParaAtualizar.get().setStatus(StatusEnum.FINALIZADO);
				}
			}

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
			Jogador savedJogador = jogadorService.saveJogador(criarJogador(jogador));
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

    public void sendSala(Sala sala) throws JsonInvalidoException{
        ObjectMapper mapper = new ObjectMapper();
        String salaAsJSON;
        String url = "/gameplay/game-update/" + sala.getHash();
        try{
            salaAsJSON = mapper.writeValueAsString(sala);
        } catch (JsonProcessingException e){
            throw new JsonInvalidoException("Não foi possível construir o JSON da sala.");
        }
        
        template.convertAndSend(url, salaAsJSON);
    }
    public Optional<Sala> iniciarPartida(Sala sala) throws JogoInvalidoException{
    		Optional<Sala> salaParaAtualizar = this.salaService.findSalaByHash(sala.getHash());
    	try {
    		if(salaParaAtualizar.isPresent()) {
    			
    			salaParaAtualizar.get().setStatus(StatusEnum.JOGANDO);
    			this.salaService.saveSala(salaParaAtualizar.get());
    			
    			return salaParaAtualizar;
    		}
    	}catch(Exception e) {
    		throw new JogoInvalidoException("Sala não encontrada");
    	}
    	
    	return salaParaAtualizar;
    
    }
}