package codigo.SSJ5.src;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MenuTest {
    @Test
    public void testExibirMenuCorreto() {
        Menu menu = new Menu();
        String menuString = menu.exibirMenu();
        assertTrue(menuString.contains("Moqueca de Palmito"));
    }

    @Test
    public void testExibirMenuErrado() {
        Menu menu = new Menu();
        String menuString = menu.exibirMenu();
        assertFalse(menuString.contains("Frango assado"));
    }

    @Test
    public void testGetProdutoByIdValido() {
        Menu menu = new Menu();
        Produto produto = menu.getProdutoById(1);
        assertNotEquals(null, produto);
    }

    @Test
    public void testGetProdutoByIdInvalido() {
        Menu menu = new Menu();
        Produto produto = menu.getProdutoById(-1);
        assertEquals(null, produto);
    }

    @Test
    public void testGetProdutoByNameValido() {
        Menu menu = new Menu();
        Produto produto = menu.getProdutoByName("Strogonoff de Cogumelos");
        assertNotEquals(null, produto);
    }

    @Test
    public void testGetProdutoByNameInvalido() {
        Menu menu = new Menu();
        Produto produto = menu.getProdutoByName("Pizza");
        assertEquals(null, produto);
    }
}