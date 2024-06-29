package com.ssj5.lpm.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class PedidoAbertoTest {

    private PedidoAberto pedidoAberto;

    @BeforeEach
    public void setUp() {
        pedidoAberto = new PedidoAberto();
    }

    @Test
    public void testCalcularValorFinal() {
        Produto produto1 = new Produto(1L, "Produto A", 10.0);
        Produto produto2 = new Produto(2L, "Produto B", 15.0);

        pedidoAberto.addProduto(produto1);
        pedidoAberto.addProduto(produto2);

        double valorFinalCalculado = pedidoAberto.calcularValorFinal();
        assertEquals(27.5, valorFinalCalculado, 0.01);
    }

    @Test
    public void testValorIndividual() {
        Produto produto1 = new Produto(1L, "Produto A", 10.0);
        Produto produto2 = new Produto(2L, "Produto B", 15.0);

        pedidoAberto.addProduto(produto1);
        pedidoAberto.addProduto(produto2);

        double valorIndividualCalculado = pedidoAberto.valorIndividual();
        assertEquals(27.5, valorIndividualCalculado, 0.01);
    }

    @Test
    public void testAddProduto() {
        Produto produto1 = new Produto(1L, "Produto A", 10.0);
        Produto produto2 = new Produto(2L, "Produto B", 15.0);

        pedidoAberto.addProduto(produto1);
        pedidoAberto.addProduto(produto2);

        List<Produto> produtosDoPedido = pedidoAberto.getProdutos();
        assertEquals(2, produtosDoPedido.size());
        assertTrue(produtosDoPedido.contains(produto1));
        assertTrue(produtosDoPedido.contains(produto2));
    }

    @Test
    public void testFormatPedido() {
        Produto produto1 = new Produto(1L, "Produto A", 10.0);
        Produto produto2 = new Produto(2L, "Produto B", 15.0);

        pedidoAberto.addProduto(produto1);
        pedidoAberto.addProduto(produto2);

        String formatoEsperado = "Itens do Pedido:\n" +
                " - Produto A: R$10,00\n" +
                " - Produto B: R$15,00\n" +
                "Preço Total: R$27,50\n" +
                "Preço por pessoa: R$27,50\n";

        assertEquals(formatoEsperado, pedidoAberto.formatPedido());
    }
}
