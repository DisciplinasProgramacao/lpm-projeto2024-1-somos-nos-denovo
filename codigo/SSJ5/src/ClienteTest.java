package codigo.SSJ5.src;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    @Test
    public void testGetNomeCorreto() {
        Cliente cliente = new Cliente("Zico");
        assertEquals("Zico", cliente.getNome());
    }

    @Test
    public void testGetNomeErrado() {
        Cliente cliente = new Cliente("Gabigol");
        assertNotEquals("Arrascaeta", cliente.getNome());
    }

    @Test
    public void testSetNomeCorreto() {
        Cliente cliente = new Cliente("Bruno Henrique");
        cliente.setNome("Cebolinha");
        assertEquals("Cebolinha", cliente.getNome());
    }

    @Test
    public void testSetNomeErrado() {
        Cliente cliente = new Cliente("Gerson");
        cliente.setNome("Delacruz");
        assertNotEquals("Gerson", cliente.getNome());
    }

    @Test
    public void testGetIdCorreto() {
        Cliente cliente1 = new Cliente("Petkovic");
        Cliente cliente2 = new Cliente("Adriano");
        assertEquals(5, cliente1.getId());
        assertEquals(6, cliente2.getId());
    }

    @Test
    public void testGetIdErrado() {
        Cliente cliente1 = new Cliente("Junior");
        Cliente cliente2 = new Cliente("Nunes");
        assertNotEquals(0, cliente1.getId());
        assertNotEquals(1, cliente2.getId());
    }
}
