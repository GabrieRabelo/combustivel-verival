package com.quarteto.o;

public class DepComb {

    public enum SITUACAO {NORMAL, SOBRAVISO, EMERGENCIA}

    public enum TIPOPOSTO {COMUM, ESTRATEGICO}

    public static final int MAX_ADITIVO = 500;
    public static final int MAX_ALCOOL = 2500;
    public static final int MAX_GASOLINA = 10000;

    private int tAditivo;
    private int tGasolina;
    private int tAlcool1;
    private int tAlcool2;

    public DepComb(int tAditivo, int tGasolina, int tAlcool1, int tAlcool2) {
        this.tAditivo = tAditivo;
        this.tGasolina = tGasolina;
        this.tAlcool1 = tAlcool1;
        this.tAlcool2 = tAlcool2;
    }

    public void defineSituacao() {
    }

    public SITUACAO getSituacao() {
        return null;
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
        if (qtdade < 0) {
            return -1;
        }
        if (qtdade + tAditivo <= MAX_ADITIVO) {
            tAditivo += qtdade;
            return qtdade;
        }
        if (qtdade + tAditivo > MAX_ADITIVO) {
            int aditivoOld = tAditivo;
            tAditivo = MAX_ADITIVO;
            return MAX_ADITIVO - aditivoOld;
        }
        return -1;
    }

    public int recebeGasolina(int qtdade) {
        if (qtdade < 0) {
            return -1;
        }
        if (qtdade + tGasolina <= MAX_GASOLINA) {
            tGasolina += qtdade;
            return qtdade;
        }
        if (qtdade + tGasolina > MAX_GASOLINA) {
            int tGasolinaOld = tGasolina;
            tGasolina = MAX_GASOLINA;
            return MAX_GASOLINA - tGasolinaOld;
        }
        return -1;
    }

    public int recebeAlcool(int qtdade) {
        if (qtdade < 0) {
            return -1;
        }
        if (qtdade + tAlcool <= MAX_ALCOOL) {
            tAlcool += qtdade;
            return qtdade;
        }
        if (qtdade + tAlcool > MAX_ALCOOL) {
            int tAlcoo1old = tAlcool;
            tAlcool = MAX_ALCOOL;
            return MAX_ALCOOL - tAlcoolold;
        }
        return -1;
    }

    public int[] encomendaCombustivel(int qtdade, TIPOPOSTO tipoPosto) {
        return new int[]{-1};
    }
}
