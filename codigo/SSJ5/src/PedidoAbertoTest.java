import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PedidoAbertoTest {

    private PedidoAberto pedidoAberto;
    private Menu menu;

    @BeforeEach
    public void setUp() {
        pedidoAberto = new PedidoAberto();
        menu = new Menu(); 
    }

    @Test
    public void testCalcularValorFinalSemProduto() {
        assertEquals(0, pedidoAberto.calcularValorFinal(), 0.01);
    }

    @Test
    public void testAddProdutoInvalido() {
        Produto produtoInvalido = new Produto("MACARRÃO", 15);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> pedidoAberto.addProduto(produtoInvalido));
        assertEquals("Opção não está disponível no menu fechado.", exception.getMessage());
    }
}
