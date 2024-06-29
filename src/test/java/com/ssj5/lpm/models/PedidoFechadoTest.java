package com.ssj5.lpm.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class PedidoFechadoTest {

    private PedidoFechado pedidoFechado;

    @BeforeEach
    public void setUp() {
        pedidoFechado = new PedidoFechado();
    }

    @Test
    public void testCalcularValorFinal() {
        double valorFinalCalculado = pedidoFechado.calcularValorFinal();
        assertEquals(35.2, valorFinalCalculado, 0.01); 
    }

    @Test
    public void testValorIndividual() {
        double valorIndividualCalculado = pedidoFechado.valorIndividual();
        assertEquals(35.2, valorIndividualCalculado, 0.01); 
    }

    @Test
    public void testAddProduto() {
        Produto produto1 = new Produto(1L, "Produto A", 10.0);
        Produto produto2 = new Produto(2L, "Produto B", 15.0);

        pedidoFechado.addProduto(produto1);
        pedidoFechado.addProduto(produto2);

        List<Produto> produtosDoPedido = pedidoFechado.getProdutos();
        assertEquals(2, produtosDoPedido.size());
        assertTrue(produtosDoPedido.contains(produto1));
        assertTrue(produtosDoPedido.contains(produto2));
    }

    @Test
    public void testFormatPedido() {
        Produto produto1 = new Produto(1L, "Produto A", 10.0);
        Produto produto2 = new Produto(2L, "Produto B", 15.0);

        pedidoFechado.addProduto(produto1);
        pedidoFechado.addProduto(produto2);

        String formatoEsperado = "Itens do Pedido:\n" +
                " - Produto A: R$10,00\n" +
                " - Produto B: R$15,00\n" +
                "Preço Total: R$35,20\n" +
                "Preço por pessoa: R$35,20\n";

        assertEquals(formatoEsperado, pedidoFechado.formatPedido());
    }
}