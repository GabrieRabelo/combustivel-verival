package com.quarteto.o;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    /* ********** recebe Aditivo ********** */
    @Test
    public void recebeAditivoEDeveriaRetornarMenosUm() {
        //Arrange
        DepComb deposito = new DepComb(400, 400, 500, 100);
        //Act
        int result = deposito.recebeAditivo(-1);
        //Assert
        assertEquals(-1, result);

    }

    @Test
    public void recebeAditivoEDeveriaRetornarQuantidade() {
        //Arrange
        DepComb deposito = new DepComb(430, 50, 50, 50);
        //Act
        int result = deposito.recebeAditivo(50);
        //Assert
        assertEquals(50, result);

        assertEquals(480, deposito.gettAditivo());

    }

    @Test
    public void recebeAditivoEDeveriaRetornarASobra() {
        //Arrange
        DepComb deposito = new DepComb(400, 50, 50, 50);
        //Act
        int result = deposito.recebeAditivo(150);
        //Assert
        assertEquals(100, result);

        assertEquals(500, deposito.gettAditivo());
    }

    /* ********** recebe Gasolina ********** */
    @Test
    public void recebeGasolinaEDevolveMenosUm() {
        //Arrange
        DepComb deposito = new DepComb(400, 5000, 50, 50);
        //Act
        int result = deposito.recebeGasolina(-1);
        //Assert
        assertEquals(-1, result);
    }

    @Test
    public void recebeGasolinaEDeveriaRetornarQuantidade() {
        //Arrange
        DepComb deposito = new DepComb(400, 4500, 50, 50);
        //Act
        int result = deposito.recebeGasolina(5000);
        //Assert
        assertEquals(5000, result);

        assertEquals(9500, deposito.gettGasolina());
    }

    @Test
    public void recebeGasolinaEDeveriaRetornaSobra() {
        //Arrange
        DepComb deposito = new DepComb(400, 6000, 50, 50);
        //Act
        int result = deposito.recebeGasolina(5000);
        //Assert
        assertEquals(4000, result);

        assertEquals(10000, deposito.gettGasolina());
    }
    /* ********** recebe Alcool ********** */


}
