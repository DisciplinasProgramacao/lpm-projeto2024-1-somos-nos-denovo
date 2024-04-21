import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse; // Import assertFalse explicitly

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class MesaTest {

    @Test
    public void testConstrutor() {
        Mesa mesa = new Mesa(4, true);
        assertNotNull(mesa);
        assertEquals(4, mesa.getCapacidade());
        assertTrue(mesa.isDisponibilidade());
    }

    @Test
    public void testGetId() {
        Mesa mesa1 = new Mesa(4, true);
        Mesa mesa2 = new Mesa(6, false);
        Mesa mesa3 = new Mesa(2, true);
        
        // Verifica se os IDs são únicos
        AtomicInteger expectedId = new AtomicInteger(1);
        assertEquals(expectedId.getAndIncrement(), mesa1.getId());
        assertEquals(expectedId.getAndIncrement(), mesa2.getId());
        assertEquals(expectedId.getAndIncrement(), mesa3.getId());
    }

    @Test
    public void testSetCapacidade() {
        Mesa mesa = new Mesa(4, true);
        assertEquals(4, mesa.getCapacidade());

        mesa.setCapacidade(6);
        assertEquals(6, mesa.getCapacidade());
    }

    @Test
    public void testSetDisponibilidade() {
        Mesa mesa = new Mesa(4, true);
        assertTrue(mesa.isDisponibilidade());

        mesa.setDisponibilidade(false);
        assertFalse(mesa.isDisponibilidade()); // Use assertFalse explicitly to improve readability
    }

    @Test
    public void testSetRequisicao() {
        Mesa mesa = new Mesa(4, true);
        Cliente cliente2 = new Cliente("Cliente2", 1); // Create a new cliente for testing
        Requisicao requisicao = new Requisicao(5, cliente2, LocalDate.of(2024, 4, 11), LocalTime.of(20, 0), LocalTime.of(22, 0)); // Create a new requisicao for testing
        
        mesa.setRequisicao(requisicao);
        assertEquals(requisicao, mesa.getRequisicao());
    }
}
