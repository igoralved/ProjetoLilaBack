package com.db.jogo.service;

<<<<<<< HEAD
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.TreeSet;
import java.util.UUID;
=======
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.db.jogo.model.*;
>>>>>>> origin/US055/US059-compra-um-coracao-grande

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

<<<<<<< HEAD
import com.db.jogo.model.Baralho;
import com.db.jogo.model.CartaDoJogo;
import com.db.jogo.model.CartaInicio;
import com.db.jogo.model.CartaObjetivo;
import com.db.jogo.model.Jogador;
import com.db.jogo.model.Jogador.StatusEnumJogador;
import com.db.jogo.model.Sala;

import io.restassured.internal.http.Status;
=======
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;
>>>>>>> origin/US055/US059-compra-um-coracao-grande

@ExtendWith(MockitoExtension.class)
public class SalaServiceTest {

<<<<<<< HEAD
	@Mock
	private SalaService salaService;

	CartaInicio cartaInicio = new CartaInicio();
	Baralho baralho = new Baralho();
	CartaDoJogo carta = new CartaDoJogo();
	CartaObjetivo cartaObjetivo = new CartaObjetivo();
	Jogador jogador = new Jogador();
	Sala sala = new Sala();

	@BeforeEach
	public void init() {
		cartaInicio.setId(UUID.randomUUID());
		cartaInicio.setNome("Teste");
		cartaInicio.setDescricao("Descricao");

		carta.setId(UUID.randomUUID());
		carta.setPontos(2);
		carta.setBonus(true);
		carta.setCategoria("Visual");
		carta.setTexto("Deficiencia visual");
		carta.setFonte("Wikipedia");
		carta.setValorCorGrande(0);
		carta.setValorCorPequeno(0);
		carta.setTipo("Ação");

		cartaObjetivo.setId(UUID.randomUUID());
		cartaObjetivo.setDescricao("Texto da carta");
		cartaObjetivo.setPontos(0);
		cartaObjetivo.setClassificacao("Ganhe pontos");
		cartaObjetivo.setCategoria("Física");

		baralho.setId(UUID.randomUUID());
		baralho.setCodigo("LILA");
		baralho.setTitulo("Teste");
		baralho.setDescricao("Exemplo");

		baralho.setCartasInicio(new ArrayList<CartaInicio>());
		baralho.adicionarCartaDoInicio(cartaInicio);
		baralho.setCartasDoJogo(new ArrayList<CartaDoJogo>());
		baralho.adicionarCartadoJogo(carta);
		baralho.setCartasObjetivo(new ArrayList<CartaObjetivo>());

		baralho.adicionarCartaDoInicio(cartaInicio);

		jogador.setId(UUID.randomUUID());
		jogador.setNome("Felipe");
		jogador.setPontos(0);
		jogador.setBonusCoracaoGra(0);
		jogador.setBonusCoracaoPeq(0);
		jogador.setCoracaoGra(0);
		jogador.setCoracaoPeq(0);
		jogador.setCartasDoJogo(new ArrayList<CartaDoJogo>());
		jogador.adicionaCarta(carta);
		jogador.adicionaObjetivo(cartaObjetivo);
		jogador.setStatus(StatusEnumJogador.ESPERANDO);

		sala.setId(UUID.randomUUID());
		sala.setBaralho(baralho);
		sala.setHash("hashpraentrar");

		sala.setStatus(Sala.StatusEnum.NOVO);
		sala.setJogadores(new ArrayList<Jogador>());
		sala.adicionarJogador(jogador);
		sala.setDado(2);

	}

	Optional<Sala> salaLocalizada;

	@Test
	@DisplayName("Teste para encontrar uma sala por Hash")
	void encontrarSalaPorHash() {

		salaLocalizada = salaService.findSalaByHash("hashpraentrar");
		assertEquals(salaLocalizada, salaService.findSalaByHash("hashpraentrar"));

	}

	@DisplayName("Teste para criar uma sala do Service")
	@Test
	void criarSala() {
		when(salaService.saveSala(sala)).thenReturn(sala);
		;
		assertEquals(sala, salaService.saveSala(sala));
	}

	@DisplayName("Teste de erro do retorno da sala")
	@Test
	void encontrarSalaPorHashComErro() {
		salaLocalizada = salaService.findSalaByHash("ertfvygbhnj");
		assertFalse(salaLocalizada.isPresent());
	}

	@DisplayName("Teste de erro do SAVE do Service")
	@Test
	void criarSalaComErro() {
		when(salaService.saveSala(null)).thenReturn(null);
		assertNull(salaService.saveSala(null));
	}
=======
    @Mock
    private SalaService salaService;

    CartaInicio cartaInicio = new CartaInicio();
    Baralho baralho = new Baralho();
    CartaDoJogo carta = new CartaDoJogo();
    CartaObjetivo cartaObjetivo = new CartaObjetivo();
    Jogador jogador = new Jogador();
    Sala sala = new Sala();

    @BeforeEach
    public void init(){
        cartaInicio.setId(UUID.randomUUID());
        cartaInicio.setNome("Teste");
        cartaInicio.setDescricao("Descricao");

        carta.setId(UUID.randomUUID());
        carta.setPontos(2);
        carta.setBonus(true);
        carta.setCategoria("Visual");
        carta.setTexto("Deficiencia visual");
        carta.setFonte("Wikipedia");
        carta.setValorCorGrande(0);
        carta.setValorCorPequeno(0);
        carta.setTipo("Ação");

        cartaObjetivo.setId(UUID.randomUUID());
        cartaObjetivo.setDescricao("Texto da carta");
        cartaObjetivo.setPontos(0);
        cartaObjetivo.setClassificacao("Ganhe pontos");
        cartaObjetivo.setCategoria("Física");

        baralho.setId(UUID.randomUUID());
        baralho.setCodigo("LILA");
        baralho.setTitulo("Teste");
        baralho.setDescricao("Exemplo");
        baralho.setCartasInicio(new ArrayList<>());
        baralho.adicionarCartaDoInicio(cartaInicio);
        baralho.setCartasDoJogo(new ArrayList<>());
        baralho.adicionarCartadoJogo(carta);
        baralho.setCartasObjetivo(new ArrayList<>());
        baralho.adicionarCartaDoInicio(cartaInicio);

        jogador.setId(UUID.randomUUID());
        jogador.setNome("Felipe");
        jogador.setPontos(0);
        jogador.setBonusCoracaoGra(0);
        jogador.setBonusCoracaoPeq(0);
        jogador.setCoracaoGra(0);
        jogador.setCoracaoPeq(0);
        jogador.setCartasDoJogo(new ArrayList());
        jogador.adicionaCarta(carta);
        jogador.adicionaObjetivo(cartaObjetivo);

        sala.setId(UUID.randomUUID());
        sala.setBaralho(baralho);
        sala.setHash("hashpraentrar");
        sala.setStatusEnum(Sala.StatusEnum.NOVO);
        sala.setJogadores(new ArrayList<>());
        sala.adicionarJogador(jogador);
    }



    Optional<Sala> salaLocalizada;


    @Test
    @DisplayName("Teste para encontrar uma sala por Hash")
    void encontrarSalaPorHash() {
        salaLocalizada = salaService.findSalaByHash("iuervnijr0f");
        assertEquals(salaLocalizada, salaService.findSalaByHash("iuervnijr0f"));
    }

     @DisplayName("Teste para criar uma sala do Service")
     @Test
     void criarSala(){
         when(salaService.saveSala(sala)).thenReturn(sala);;
         assertEquals(sala, salaService.saveSala(sala));
     }

     @DisplayName("Teste de erro do retorno da sala")
     @Test
     void encontrarSalaPorHashComErro() {
         salaLocalizada = salaService.findSalaByHash("ertfvygbhnj");
         assertFalse(salaLocalizada.isPresent());
     }
 
     @DisplayName("Teste de erro do SAVE do Service")
     @Test
      void criarSalaComErro(){
         when(salaService.saveSala(null)).thenReturn(null);
         assertNull(salaService.saveSala(null));
      }
>>>>>>> origin/US055/US059-compra-um-coracao-grande
}
