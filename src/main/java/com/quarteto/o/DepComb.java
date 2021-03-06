package com.quarteto.o;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DepComb {

	public enum SITUACAO {NORMAL, SOBRAVISO, EMERGENCIA}

	public enum TIPOPOSTO {COMUM, ESTRATEGICO}

	public static final int MAX_ADITIVO = 500;
	public static final int MAX_ALCOOL = 2500;
	public static final int MAX_GASOLINA = 10000;
	public static final double PROPORCAO_ADITIVO = 0.05;
	public static final double PROPORCAO_GASOLINA = 0.7;
	public static final double PROPORCAO_ALCOOL = 0.25;

	private int tAditivo;
	private int tGasolina;
	private int tAlcool1;
	private int tAlcool2;

	private SITUACAO situacao;

	public DepComb(int tAditivo, int tGasolina, int tAlcool1, int tAlcool2) {
		this.tAditivo = tAditivo;
		this.tGasolina = tGasolina;
		this.tAlcool1 = tAlcool1;
		this.tAlcool2 = tAlcool2;
		defineSituacao();
	}

	public void defineSituacao() {
		double menor = procuraMaisEscasso();

		if (menor >= 0.5) {
			situacao = SITUACAO.NORMAL;
		} else if (menor >= 0.25) {
			situacao = SITUACAO.SOBRAVISO;
		} else {
			situacao = SITUACAO.EMERGENCIA;
		}
	}

	private double procuraMaisEscasso() {
		List<Double> combustiveis = new ArrayList<>();
		combustiveis.add((double) tAditivo / MAX_ADITIVO);
		combustiveis.add((double) tGasolina / MAX_GASOLINA);
		combustiveis.add((double) (tAlcool1 + tAlcool2) / MAX_ALCOOL);
		return Collections.min(combustiveis);
	}

	public SITUACAO getSituacao() {
		return situacao;
	}

	public int gettAditivo() {
		return tAditivo;
	}

	public int gettGasolina() {
		return tGasolina;
	}

	public int gettAlcool1() {
		return tAlcool1;
	}

	public int gettAlcool2() {
		return tAlcool2;
	}

	public int recebeAditivo(int qtdade) {
		int val = -1;
		if (qtdade < 0) {
			return val;
		}

		if (qtdade + tAditivo <= MAX_ADITIVO) {
			tAditivo += qtdade;
			val = qtdade;
		} else {
			int aditivoOld = tAditivo;
			tAditivo = MAX_ADITIVO;
			val = MAX_ADITIVO - aditivoOld;
		}
		defineSituacao();
		return val;
	}

	public int recebeGasolina(int qtdade) {
		int val = -1;
		if (qtdade < 0) {
			return val;
		}

		if (qtdade + tGasolina <= MAX_GASOLINA) {
			tGasolina += qtdade;
			val = qtdade;
		} else {
			int tGasolinaOld = tGasolina;
			tGasolina = MAX_GASOLINA;
			val = MAX_GASOLINA - tGasolinaOld;
		}
		defineSituacao();
		return val;
	}

	public int recebeAlcool(int qtdade) {
		int val = -1;
		if (qtdade < 0) {
			return val;
		}

		int tAlcool = tAlcool1 + tAlcool2;
		if (qtdade + tAlcool <= MAX_ALCOOL) {
			tAlcool1 += (double) qtdade / 2;
			tAlcool2 += (double) qtdade / 2;
			val = qtdade;
		} else {
			int tAlcoolold = tAlcool1 + tAlcool2;
			tAlcool1 = MAX_ALCOOL;
			tAlcool2 = MAX_ALCOOL;
			val = MAX_ALCOOL - tAlcoolold;
		}
		return val;
	}

	public int[] encomendaCombustivel(int qtdade, TIPOPOSTO tipoPosto) {
		int[] erro = new int[1];
		int[] remanescente = new int[4];

		double qtdadeAditivo = qtdade * PROPORCAO_ADITIVO;
		double qtdadeGasolina = qtdade * PROPORCAO_GASOLINA;
		double qtdadeAlcool = qtdade * PROPORCAO_ALCOOL;

		if (qtdade < 0) {
			erro[0] = -1;
			return erro;
		}

		if (situacao == SITUACAO.NORMAL) {
			if (verificaGasolina(qtdadeGasolina) && verificaAlcool(qtdadeAlcool) && verificaAditivo(qtdadeAditivo)) {
				tAditivo -= qtdadeAditivo;
				tGasolina -= qtdadeGasolina;
				tAlcool1 -= qtdadeAlcool / 2;
				tAlcool2 -= qtdadeAlcool / 2;
				remanescente[0] = tAditivo;
				remanescente[1] = tGasolina;
				remanescente[2] = tAlcool1;
				remanescente[3] = tAlcool2;

				defineSituacao();
				return remanescente;
			} else {
				erro[0] = -3;
				return erro;
			}
		} else if (situacao == SITUACAO.SOBRAVISO) {
			if (tipoPosto == TIPOPOSTO.COMUM) {
				qtdadeAditivo = qtdadeAditivo / 2;
				qtdadeGasolina = qtdadeGasolina / 2;
				qtdadeAlcool = qtdadeAlcool / 2;
			}

			if (verificaGasolina(qtdadeGasolina) && verificaAlcool(qtdadeAlcool) && verificaAditivo(qtdadeAditivo)) {
				tAditivo -= qtdadeAditivo;
				tGasolina -= qtdadeGasolina;
				tAlcool1 -= qtdadeAlcool / 2;
				tAlcool2 -= qtdadeAlcool / 2;
				remanescente[0] = tAditivo;
				remanescente[1] = tGasolina;
				remanescente[2] = tAlcool1;
				remanescente[3] = tAlcool2;

				defineSituacao();
				return remanescente;
			} else {
				erro[0] = -3;
				return erro;
			}
		}

		if (tipoPosto == TIPOPOSTO.COMUM) {
				erro[0] = -2;
				return erro;
			} else {
				if (verificaGasolina(qtdadeGasolina) && verificaAlcool(qtdadeAlcool)) {
					if (verificaAditivo(qtdadeAditivo)) {
						tAditivo -= qtdadeAditivo;
					}
					tGasolina -= qtdadeGasolina;
					tAlcool1 -= qtdadeAlcool / 2;
					tAlcool2 -= qtdadeAlcool / 2;
					remanescente[0] = tAditivo;
					remanescente[1] = tGasolina;
					remanescente[2] = tAlcool1;
					remanescente[3] = tAlcool2;

					defineSituacao();
					return remanescente;
				} else {
					erro[0] = -3;
					return erro;
				}
			}
	}

	private boolean verificaGasolina(double qtdadeGasolina) {
		return tGasolina - qtdadeGasolina >= 0;
	}

	private boolean verificaAlcool(double qtdadeAlcool) {
		return (tAlcool1 + tAlcool2) - qtdadeAlcool >= 0;
	}

	private boolean verificaAditivo(double qtdadeAditivo) {
		return this.tAditivo - qtdadeAditivo >= 0;
	}
}
