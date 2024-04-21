import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class ClienteTest {

    @Test
    public void retornarNomeCorretamente() {
        Cliente cliente = new Cliente("João Caram",9);
        assertEquals("João Caram", cliente.getNome());
    }

    @Test
    public void retornarNomeTratado() {
        Cliente cliente = new Cliente("lucas cerqueira azevedo",8);
        assertEquals("Lucas Cerqueira Azevedo", cliente.getNome());
    }
}
