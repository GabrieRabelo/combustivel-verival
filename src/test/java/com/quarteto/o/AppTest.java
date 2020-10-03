package com.quarteto.o;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */
class AppTest {
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
}
