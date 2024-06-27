package com.ssj5.lpm.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {

    private Menu menu;

    @BeforeEach
    public void setUp() {
        // Mock de menu com alguns produtos
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto(1L, "Produto 1", 10.0));
        produtos.add(new Produto(2L, "Produto 2", 15.0));
        produtos.add(new Produto(3L, "Produto 3", 20.0));

        // Implementação da classe abstrata Menu para testes
        menu = new Menu() {
            {
                this.menu = produtos;
            }
        };
    }

    @Test
    public void testGetMenu() {
        List<Produto> produtosDoMenu = menu.getMenu();
        assertNotNull(produtosDoMenu);
        assertEquals(3, produtosDoMenu.size());
    }

    @Test
    public void testGetProdutoById() {
        // Teste para encontrar um produto pelo ID válido
        Produto produtoEncontrado = menu.getProdutoById(2L);
        assertNotNull(produtoEncontrado);
        assertEquals("Produto 2", produtoEncontrado.getNome());

        // Teste para produto não encontrado
        Produto produtoNaoEncontrado = menu.getProdutoById(5L);
        assertNull(produtoNaoEncontrado);
    }

    @Test
    public void testExibirMenu() {
        String menuString = menu.exibirMenu();
        assertTrue(menuString.contains("1 - Produto 1: R$10.0"));
        assertTrue(menuString.contains("2 - Produto 2: R$15.0"));
        assertTrue(menuString.contains("3 - Produto 3: R$20.0"));
    }
}
