import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ClienteTest {

    private Cliente cliente;

    @Before
    public void setUp() {
        cliente = new Cliente("Zico");
    }

    @Test
    public void testConstructorAndGetNome_Positive() {
        assertEquals("Zico", cliente.getNome());
    }

    @Test
    public void testConstructorAndGetNome_Negative() {
        assertNotEquals("Pelé", cliente.getNome());
    }

    @Test
    public void testSetNome_Positive() {
        cliente.setNome("Júnior");
        assertEquals("Júnior", cliente.getNome());
    }

    @Test
    public void testSetNome_Negative() {
        cliente.setNome("Leandro");
        assertNotEquals("Zico", cliente.getNome());
    }

    @Test
    public void testGetId_Positive() {
        assertEquals(0, cliente.getId());
    }

    @Test
    public void testGetId_Negative() {
        assertNotEquals(1, cliente.getId());
    }
}
