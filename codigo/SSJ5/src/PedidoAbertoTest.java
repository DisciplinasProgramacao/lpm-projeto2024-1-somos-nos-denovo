package codigo.SSJ5.src;

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
}