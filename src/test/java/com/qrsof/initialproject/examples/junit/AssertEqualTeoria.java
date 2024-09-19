package com.qrsof.initialproject.examples.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AssertEqualTeoria {


    @Test
    public void miTest() {
        assertEquals(1, 1);
        // assertEquals(2, 3);
//Compara que los valores del metodo sean iguales
    }
    @Test
    public void notAssertEqual(){
        //assertNotEquals("hola", "hola");
        assertNotEquals(2,1);
    }
}
