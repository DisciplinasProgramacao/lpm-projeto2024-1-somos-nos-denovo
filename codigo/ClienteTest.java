package codigo;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class ClienteTest {

    @Test
    public void retornarNomeCorretamente() {
        Cliente cliente = new Cliente("João Caram");
        assertEquals("João Caram", cliente.getNome());
    }

    @Test
    public void testGerarRequisicao() {
        Cliente cliente = new Cliente("Nome do Cliente");
        Requisicao requisicao = cliente.gerarRequisicao(8);
        assertEquals(8, requisicao.getQuantidade());
    }
}
