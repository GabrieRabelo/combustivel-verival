package com.quarteto.o;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */
class DepCombTest {
	@DisplayName("Aditivo - Deveria retornar -1 ao receber quantidade inválida")
	@Test
	void recebeAditivoEDeveriaRetornarMenosUm() {
		DepComb deposito = new DepComb(400, 400, 500, 100);

		int result = deposito.recebeAditivo(-1);

		assertEquals(-1, result);
	}

	@DisplayName("Aditivo - Deveria retornar a quantidade adicionada com sucesso")
	@ParameterizedTest
	@CsvSource({
			"430, 50, 50, 480",  // Sem sobrar
			"400, 150, 100, 500" // Sobrando
	})
	void recebeAditivoEDeveriaRetornarQuantidade(int tAditivo, int qtdade, int expected1, int expected2) {
		DepComb deposito = new DepComb(tAditivo, 50, 50, 50);

		int result = deposito.recebeAditivo(qtdade);

		assertEquals(expected1, result);
		assertEquals(expected2, deposito.gettAditivo());
	}

	@DisplayName("Gasolina - Deveria retornar -1 ao receber quantidade inválida")
	@Test
	void recebeGasolinaEDevolveMenosUm() {
		DepComb deposito = new DepComb(400, 5000, 50, 50);

		int result = deposito.recebeGasolina(-1);

		assertEquals(-1, result);
	}

	@DisplayName("Gasolina - Deveria retornar a quantidade adicionada com sucesso")
	@ParameterizedTest
	@CsvSource({
			"4500, 5000, 5000, 9500", // Sem sobrar
			"6000, 6000, 4000, 10000" // Sobrando
	})
	void recebeGasolinaEDeveriaRetornarQuantidade(int tGasolina, int qtdade, int expected1, int expected2) {
		DepComb deposito = new DepComb(400, tGasolina, 50, 50);

		int result = deposito.recebeGasolina(qtdade);

		assertEquals(expected1, result);
		assertEquals(expected2, deposito.gettGasolina());
	}

	@DisplayName("Alcool - Deveria retornar -1 ao receber quantidade inválida")
	@Test
	void recebeAlcoolInvalidoEDeveriaRetornarMenosUm() {
		DepComb deposito = new DepComb(400, 400, 500, 100);

		int result = deposito.recebeAlcool(-1);

		assertEquals(-1, result);
	}

	@DisplayName("Alcool - Deveria retornar a quantidade adicionada com sucesso")
	@ParameterizedTest
	@CsvSource({
			"800, 100, 100, 850",   // Sem sobrar
			"1000, 2500, 500, 2500" // Sobrando
	})
	void recebeAlcoolEDeveriaRetornarQuantidade(int tAlcool, int qtdade, int expected1, int expected2) {
		DepComb deposito = new DepComb(430, 50, tAlcool, tAlcool);

		int result = deposito.recebeAlcool(qtdade);

		assertEquals(expected1, result);
		assertEquals(expected2, deposito.gettAlcool1());
		assertEquals(expected2, deposito.gettAlcool2());
	}

	@DisplayName("Define situaçao - Deveria modificar as situações.")
	@ParameterizedTest
	@CsvSource({
			"8000, NORMAL",
			"4500, SOBRAVISO",
			"2000, EMERGENCIA"
	})
	void defineSituacaoDeveriaModificarSituacao(int tGasolina, DepComb.SITUACAO situacao) {

		DepComb deposito = new DepComb(450, tGasolina, 750, 750);

		var result = deposito.getSituacao();
		assertEquals(situacao, result);
	}

	@DisplayName("Encomenda - Deveria retonar vetor com código de erro.")
	@ParameterizedTest
	@CsvSource({
			"400, 8000, 1000, -1, -1, COMUM",           // erro -1 - Quantidade inválida
			"250, 5000, 625, 10000, -3, COMUM",         // erro -3 - Impossível atender quantidade maior que o que tem disponível
			"200, 4000, 400, 10000, -3, ESTRATEGICO",   // erro -3 - Impossível atender quantidade maior que o que tem disponível (SOBRAVISO)
			"100, 1200, 250, 2000, -3, ESTRATEGICO",    // erro -3 - Impossível atender quantidade maior que o que tem disponível (EMERGENCIA)
			"100, 2000, 250, 10000, -2, COMUM",         // erro -2 - Impossível atender posto comum situação emergencia
	})
	void encomendaCombustivelDeveRetornarVetorComCodigoDeErro(int tAditivo, int tGasolina, int tAlcool, int qtdade, int erro,
	                                                          DepComb.TIPOPOSTO tipoposto) {
		DepComb deposito = new DepComb(tAditivo, tGasolina, tAlcool, tAlcool);

		int[] result = deposito.encomendaCombustivel(qtdade, tipoposto);

		int[] expected = new int[]{erro};
		assertEquals(expected[0], result[0]);
	}

	@DisplayName("Encomenda - Deveria retonar vetor com combustivel remanescente.")
	@ParameterizedTest
	@CsvSource({
			"400, 8000, 1000, 300, 6600, 750, COMUM",       // situação NORMAL     -- 100%
			"200, 4000, 500, 150, 3300, 375, COMUM",        // situação SOBRAVISO  -- 50%
			"200, 4000, 500, 100, 2600, 250, ESTRATEGICO",  // situação SOBRAVISO  -- 100%
			"100, 2000, 250, 0, 600, 0, ESTRATEGICO",       // situação EMERGENCIA -- 100%
			"20, 2000, 250, 20, 600, 0, ESTRATEGICO"       // situação EMERGENCIA (sem aditivo)
	})
	void encomendaCombustivelDeveRetornarVetorComCombustivelRemanescente(int tAditivo, int tGasolina, int tAlcool, int expected1, int expected2,
	                                                                     int expected3, DepComb.TIPOPOSTO tipoposto) {
		DepComb deposito = new DepComb(tAditivo, tGasolina, tAlcool, tAlcool);

		int[] result = deposito.encomendaCombustivel(2000, tipoposto);

		int[] expected = new int[]{expected1, expected2, expected3, expected3};

		assertEquals(expected[0], result[0]);
		assertEquals(expected[1], result[1]);
		assertEquals(expected[2], result[2]);
		assertEquals(expected[3], result[3]);
	}

	/*Teste valor limite*/

	@DisplayName("Situacao - Deveria definir situaçao para os valores limites.")
	@ParameterizedTest
	@CsvSource({
			"500, NORMAL",      // 100% - On point NORMAL
			"250, NORMAL",      // 50% - On point NORMAL || On point SOBRAVISO ??
			"450, NORMAL",      // 90% - In point NORMAL || Outpoint SOBRAVISO
			"200, SOBRAVISO",   // 40% - Out point  NORMAL || In point SOBRAVISO
			"125, SOBRAVISO",   // 25% - On point SOBRAVISO || On point EMERGENCIA ??
			"100, EMERGENCIA",  // 20% - Out point SOBRAVISO || In point EMERGENCIA
			"0, EMERGENCIA"     // 0% - On point EMERGENCIA
	})
	void defineSituacaoNormalValoresLimites(int tAditivo, DepComb.SITUACAO situacao) {
		//menor porcentagem no tanque: 100%
		DepComb deposito = new DepComb(tAditivo, 10000, 1250, 1250);

		DepComb.SITUACAO result = deposito.getSituacao();
		assertEquals(situacao, result);
	}
}