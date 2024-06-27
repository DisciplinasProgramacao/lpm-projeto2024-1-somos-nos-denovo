package com.ssj5.lpm.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MesaTest {

    private Mesa mesa;

    @BeforeEach
    public void setUp() {
        mesa = new Mesa(4, true); 
    }

    @Test
    public void testConstrutorComParametros() {
        assertEquals(4, mesa.getCapacidade());
        assertTrue(mesa.isDisponibilidade());
    }

    @Test
    public void testGetSetId() {
        mesa.setId(1L);
        assertEquals(1L, mesa.getId());
    }

    @Test
    public void testGetSetCapacidade() {
        mesa.setCapacidade(6);
        assertEquals(6, mesa.getCapacidade());
    }

    @Test
    public void testGetSetDisponibilidade() {
        mesa.setDisponibilidade(false);
        assertFalse(mesa.isDisponibilidade());
    }

    @Test
    public void testDesocupar() {
        mesa.desocupar();
        assertTrue(mesa.isDisponibilidade());
    }

    @Test
    public void testFalse() {
        Mesa outraMesa = new Mesa(4, true);
        assertFalse(mesa.equals(outraMesa));
    }

    @Test
    public void testHashCodeNotEquals() {
        Mesa outraMesa = new Mesa(4, true);
        assertNotEquals(mesa.hashCode(), outraMesa.hashCode());
    }
}
