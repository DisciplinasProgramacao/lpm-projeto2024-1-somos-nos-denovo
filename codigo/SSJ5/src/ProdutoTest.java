package codigo.SSJ5.src;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class ProdutoTest {

    @Test
    public void testGetIdProdutoEquals() {
        Produto produto = new Produto("Pizza", 20);
        assertEquals(0, produto.getIdProduto());
    }

    @Test
    public void testGetIdProdutoNotEquals() {
        Produto produto = new Produto("Pizza", 20);
        assertNotEquals(1, produto.getIdProduto());
    }

    @Test
    public void testGetNomeProdutoEquals() {
        Produto produto = new Produto("Pizza", 20);
        assertEquals("Pizza", produto.getNomeProduto());
    }

    @Test
    public void testGetNomeProdutoNotEquals() {
        Produto produto = new Produto("Pizza", 20);
        assertNotEquals("Hamburguer", produto.getNomeProduto());
    }

    @Test
    public void testGetPrecoProdutoEquals() {
        Produto produto = new Produto("Pizza", 20);
        assertEquals(20.0, produto.getPrecoProduto());
    }

    @Test
    public void testGetPrecoProdutoNotEquals() {
        Produto produto = new Produto("Pizza", 20);
        assertNotEquals(25.0, produto.getPrecoProduto());
    }
}