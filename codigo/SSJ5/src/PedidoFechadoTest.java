package codigo.SSJ5.src;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PedidoFechadoTest {

    private PedidoFechado pedidoFechado;

    @BeforeEach
    public void setUp() {
        pedidoFechado = new PedidoFechado();
    }

    @Test
    public void testCalcularValorFinalSemProduto() {
        assertEquals(0, pedidoFechado.calcularValorFinal(), 0.01);
    }


    @Test
    public void testAddProdutoInvalido() {
        Produto produtoInvalido = new Produto("MACARRÃO", 15);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> pedidoFechado.addProduto(produtoInvalido));
        assertEquals("Opção não está disponível no menu fechado.", exception.getMessage());
    }

}
