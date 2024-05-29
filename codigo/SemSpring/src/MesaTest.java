package codigo.SSJ5.src;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MesaTest {

    @Test
    public void testGetCapacidadeEquals() {
        Mesa mesa = new Mesa(4, true);
        assertEquals(4, mesa.getCapacidade());
    }

    @Test
    public void testGetCapacidadeNotEquals() {
        Mesa mesa = new Mesa(4, true);
        assertNotEquals(6, mesa.getCapacidade());
    }

    @Test
    public void testSetCapacidadeEquals() {
        Mesa mesa = new Mesa(4, true);
        mesa.setCapacidade(6);
        assertEquals(6, mesa.getCapacidade());
    }

    @Test
    public void testSetCapacidadeNotEquals() {
        Mesa mesa = new Mesa(4, true);
        mesa.setCapacidade(6);
        assertNotEquals(4, mesa.getCapacidade());
    }

    @Test
    public void testIsDisponibilidadeEquals() {
        Mesa mesa = new Mesa(4, true);
        assertTrue(mesa.isDisponibilidade());
    }

    @Test
    public void testIsDisponibilidadeNotEquals() {
        Mesa mesa = new Mesa(4, true);
        assertFalse(!mesa.isDisponibilidade());
    }

    @Test
    public void testSetDisponibilidadeEquals() {
        Mesa mesa = new Mesa(4, true);
        mesa.setDisponibilidade(false);
        assertFalse(mesa.isDisponibilidade());
    }

    @Test
    public void testSetDisponibilidadeNotEquals() {
        Mesa mesa = new Mesa(4, true);
        mesa.setDisponibilidade(false);
        assertNotEquals(true, mesa.isDisponibilidade());
    }

    @Test
    public void testGetIdEquals() {
        Mesa mesa = new Mesa(4, true);
        int expectedId = mesa.getId();
        assertEquals(expectedId, mesa.getId());
    }

    @Test
    public void testGetIdNotEquals() {
        Mesa mesa1 = new Mesa(4, true);
        Mesa mesa2 = new Mesa(4, true);
        assertNotEquals(mesa1.getId(), mesa2.getId());
    }
}
