package codigo.SSJ5.src;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MesaTest {

    @Test
    public void testGetCapacidadeCorreta() {
        Mesa mesa = new Mesa(4, true);
        assertEquals(4, mesa.getCapacidade());
    }

    @Test
    public void testGetCapacidadeIncorreta() {
        Mesa mesa = new Mesa(4, true);
        assertNotEquals(6, mesa.getCapacidade());
    }

    @Test
    public void testSetCapacidadeCorreta() {
        Mesa mesa = new Mesa(4, true);
        mesa.setCapacidade(6);
        assertEquals(6, mesa.getCapacidade());
    }

    @Test
    public void testSetCapacidadeIncorreta() {
        Mesa mesa = new Mesa(4, true);
        mesa.setCapacidade(6);
        assertNotEquals(4, mesa.getCapacidade());
    }

    @Test
    public void testIsDisponibilidadeCorreta() {
        Mesa mesa = new Mesa(4, true);
        assertTrue(mesa.isDisponibilidade());
    }

    @Test
    public void testIsDisponibilidadeIncorreta() {
        Mesa mesa = new Mesa(4, true);
        assertFalse(!mesa.isDisponibilidade());
    }

    @Test
    public void testSetDisponibilidadeCorreta() {
        Mesa mesa = new Mesa(4, true);
        mesa.setDisponibilidade(false);
        assertFalse(mesa.isDisponibilidade());
    }

    @Test
    public void testSetDisponibilidadeIncorreta() {
        Mesa mesa = new Mesa(4, true);
        mesa.setDisponibilidade(false);
        assertNotEquals(true, mesa.isDisponibilidade());
    }

    @Test
    public void testGetIdCorreto() {
        Mesa mesa = new Mesa(4, true);
        int expectedId = mesa.getId();
        assertEquals(expectedId, mesa.getId());
    }

    @Test
    public void testGetIdIncorreto() {
        Mesa mesa1 = new Mesa(4, true);
        Mesa mesa2 = new Mesa(4, true);
        assertNotEquals(mesa1.getId(), mesa2.getId());
    }
}
