package com.quarteto.o;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.quarteto.o.DepComb.SITUACAO.NORMAL;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */
class DepCombTest {
    @DisplayName("Aditivo - Deveria retornar -1 ao receber quantidade inválida")
    @Test
    void recebeAditivoEDeveriaRetornarMenosUm() {
        //Arrange
        DepComb deposito = new DepComb(400, 400, 500, 100);
        //Act
        int result = deposito.recebeAditivo(-1);
        //Assert
        assertEquals(-1, result);

    }

    @DisplayName("Aditivo - Deveria retornar a quantidade adicionada com sucesso")
    @Test
    void recebeAditivoEDeveriaRetornarQuantidade() {
        //Arrange
        DepComb deposito = new DepComb(430, 50, 50, 50);
        //Act
        int result = deposito.recebeAditivo(50);
        //Assert
        assertEquals(50, result);

        assertEquals(480, deposito.gettAditivo());
    }

    @DisplayName("Aditivo - Deveria retornar a quantidade adicionada sem a sobra.")
    @Test
    void recebeAditivoEDeveriaRetornarSemASobra() {
        //Arrange
        DepComb deposito = new DepComb(400, 50, 50, 50);
        //Act
        int result = deposito.recebeAditivo(150);
        //Assert
        assertEquals(100, result);

        assertEquals(500, deposito.gettAditivo());
    }

    @DisplayName("Gasolina - Deveria retornar -1 ao receber quantidade inválida")
    @Test
    void recebeGasolinaEDevolveMenosUm() {
        //Arrange
        DepComb deposito = new DepComb(400, 5000, 50, 50);
        //Act
        int result = deposito.recebeGasolina(-1);
        //Assert
        assertEquals(-1, result);
    }

    @DisplayName("Gasolina - Deveria retornar a quantidade adicionada com sucesso")
    @Test
    void recebeGasolinaEDeveriaRetornarQuantidade() {
        //Arrange
        DepComb deposito = new DepComb(400, 4500, 50, 50);
        //Act
        int result = deposito.recebeGasolina(5000);
        //Assert
        assertEquals(5000, result);

        assertEquals(9500, deposito.gettGasolina());
    }

    @DisplayName("Gasolina - Deveria retornar a quantidade adicionada sem a sobra.")
    @Test
    void recebeGasolinaEDeveriaRetornaSemASobra() {
        //Arrange
        DepComb deposito = new DepComb(400, 6000, 50, 50);
        //Act
        int result = deposito.recebeGasolina(6000);
        //Assert
        assertEquals(4000, result);

        assertEquals(10000, deposito.gettGasolina());
    }

    @DisplayName("Alcool - Deveria retornar -1 ao receber quantidade inválida")
    @Test
    void recebeAlcoolInvalidoEDeveriaRetornarMenosUm() {
        //Arrange
        DepComb deposito = new DepComb(400, 400, 500, 100);
        //Act
        int result = deposito.recebeAlcool(-1);
        //Assert
        assertEquals(-1, result);
    }

    @DisplayName("Alcool - Deveria retornar a quantidade adicionada com sucesso")
    @Test
    void recebeAlcoolEDeveriaRetornarQuantidade() {
        //Arrange
        DepComb deposito = new DepComb(430, 50, 800, 800);
        //Act
        int result = deposito.recebeAlcool(100);
        //Assert
        assertEquals(100, result);

        assertEquals(850, deposito.gettAlcool1());
        assertEquals(850, deposito.gettAlcool2());
    }

    @DisplayName("Alcool - Deveria retornar a quantidade adicionada sem a sobra.")
    @Test
    void recebeAlcoolEDeveriaRetornaSemASobra() {
        //Arrange
        DepComb deposito = new DepComb(400, 6000, 1000, 1000);
        //Act
        int result = deposito.recebeAlcool(2500);
        //Assert
        assertEquals(500, result);
        assertEquals(2500, deposito.gettAlcool1());
        assertEquals(2500, deposito.gettAlcool2());
    }
    @DisplayName("Define situaçao - Deveria modificar para normal.")
    @Test
    void defineSituacaoDeveriaModificarParaNormal(){

        DepComb deposito = new DepComb(450, 8000, 750, 750);

        var situacao = deposito.getSituacao();
        assertEquals(NORMAL, situacao);
    }

    @DisplayName("Define situaçao - Deveria modificar para sobreaviso.")
    @Test
    void defineSituacaoDeveriaModificarParaSobreaviso(){

        DepComb deposito = new DepComb(450, 4500, 750, 750);

        var situacao = deposito.getSituacao();
        assertEquals(DepComb.SITUACAO.SOBRAVISO, situacao);
    }

    @DisplayName("Define situaçao - Deveria modificar para emergencia.")
    @Test
    void defineSituacaoDeveriaModificarParaEmergencia(){

        DepComb deposito = new DepComb(450, 2000, 750, 750);

        var situacao = deposito.getSituacao();
        assertEquals(DepComb.SITUACAO.EMERGENCIA, situacao);
    }

    @DisplayName("Encomenda - Deveria retonar código de erro -1.")
    @Test
    void encomendaCombustivelDeveRetornarVetorComCodigoMenosUm(){
        DepComb deposito = new DepComb(400, 8000, 1000, 1000);

        int[] result = deposito.encomendaCombustivel(-1, DepComb.TIPOPOSTO.COMUM);

        int[] expected = new int[] {-1};
        assertEquals(expected[0], result[0]);
    }

    @DisplayName("Encomenda - Deveria retonar vetor com combustivel remanescente.")
    @Test
    void encomendaCombustivelDeveRetornarVetorComCombustivelRemanescente(){
        DepComb deposito = new DepComb(400, 8000, 1000, 1000);

        int[] result = deposito.encomendaCombustivel(2000, DepComb.TIPOPOSTO.COMUM);

        int[] expected = new int[] {300, 6600, 750, 750};

        assertEquals(expected[0], result[0]);
        assertEquals(expected[1], result[1]);
        assertEquals(expected[2], result[2]);
        assertEquals(expected[3], result[3]);
    }

    @DisplayName("Encomenda - Deveria retonar código de impossível atender.")
    @Test
    void encomendaCombustivelDeveRetornarVetorComCodigoMenosTres(){
        DepComb deposito = new DepComb(250, 5000, 625, 625);

        int[] result = deposito.encomendaCombustivel(10000, DepComb.TIPOPOSTO.COMUM);

        int[] expected = new int[] {-3};

        assertEquals(expected[0], result[0]);
    }

    @DisplayName("Encomenda - Deveria retonar vetor com combustivel remanescente na situaçao sobreaviso para posto comum.")
    @Test
    void encomendaCombustivelDeveRetornarVetorComCombustivelRemanescenteSobreavisoComum(){
        DepComb deposito = new DepComb(200, 4000, 500, 500);

        int[] result = deposito.encomendaCombustivel(2000, DepComb.TIPOPOSTO.COMUM);

        int[] expected = new int[] {150, 3300, 375, 375};

        assertEquals(expected[0], result[0]);
        assertEquals(expected[1], result[1]);
        assertEquals(expected[2], result[2]);
        assertEquals(expected[3], result[3]);
    }

    @DisplayName("Encomenda - Deveria retonar vetor com combustivel remanescente na situaçao sobreaviso para posto estrategico.")
    @Test
    void encomendaCombustivelDeveRetornarVetorComCombustivelRemanescenteSobreavisoEstrategico(){
        DepComb deposito = new DepComb(200, 4000, 500, 500);

        int[] result = deposito.encomendaCombustivel(2000, DepComb.TIPOPOSTO.ESTRATEGICO);

        int[] expected = new int[] {100, 2600, 250, 250};

        assertEquals(expected[0], result[0]);
        assertEquals(expected[1], result[1]);
        assertEquals(expected[2], result[2]);
        assertEquals(expected[3], result[3]);
    }

    @DisplayName("Encomenda - Deveria retonar código de impossível atender - Sobreaviso")
    @Test
    void encomendaCombustivelDeveRetornarVetorComCodigoMenosTresSobreaviso(){
        DepComb deposito = new DepComb(200, 4000, 500, 500);

        int[] result = deposito.encomendaCombustivel(10000, DepComb.TIPOPOSTO.ESTRATEGICO);

        int[] expected = new int[] {-3};

        assertEquals(expected[0], result[0]);
    }

    @DisplayName("Encomenda - Deveria retonar código de impossível atender posto comum - Emergencia")
    @Test
    void encomendaCombustivelDeveRetornarVetorComCodigoMenosDoisEmergencia(){
        DepComb deposito = new DepComb(100, 2000, 250, 250);

        int[] result = deposito.encomendaCombustivel(10000, DepComb.TIPOPOSTO.COMUM);

        int[] expected = new int[] {-2};

        assertEquals(expected[0], result[0]);
    }

    @DisplayName("Encomenda - Deveria retonar vetor com combustivel remanescente na situaçao emergencia para posto estrategico.")
    @Test
    void encomendaCombustivelDeveRetornarVetorComCombustivelRemanescenteEmergenciaEstrategico(){
        DepComb deposito = new DepComb(100, 2000, 250, 250);

        int[] result = deposito.encomendaCombustivel(2000, DepComb.TIPOPOSTO.ESTRATEGICO);

        int[] expected = new int[] {0, 600, 0, 0};

        assertEquals(expected[0], result[0]);
        assertEquals(expected[1], result[1]);
        assertEquals(expected[2], result[2]);
        assertEquals(expected[3], result[3]);
    }

    @DisplayName("Encomenda - Deveria retonar vetor com combustivel remanescente na situaçao emergencia para posto estrategico (sem aditivo).")
    @Test
    void encomendaCombustivelDeveRetornarVetorComCombustivelRemanescenteEmergenciaEstrategicoSemAditivo(){
        DepComb deposito = new DepComb(20, 2000, 250, 250);

        int[] result = deposito.encomendaCombustivel(2000, DepComb.TIPOPOSTO.ESTRATEGICO);

        int[] expected = new int[] {20, 600, 0, 0};

        assertEquals(expected[0], result[0]);
        assertEquals(expected[1], result[1]);
        assertEquals(expected[2], result[2]);
        assertEquals(expected[3], result[3]);
    }

    @DisplayName("Encomenda - Deveria retonar código de impossível atender - Emergencia")
    @Test
    void encomendaCombustivelDeveRetornarVetorComCodigoMenosTresEmergencia(){
        DepComb deposito = new DepComb(100, 1200, 250, 250);

        int[] result = deposito.encomendaCombustivel(2000, DepComb.TIPOPOSTO.ESTRATEGICO);

        int[] expected = new int[] {-3};

        assertEquals(expected[0], result[0]);
    }

}
