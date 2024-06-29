package com.ssj5.lpm.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PedidoTest {

    private Pedido pedido;

    @BeforeEach
    void setUp() {
        pedido = new Pedido() {
            @Override
            public void addProduto(Produto produto) {
            }

            @Override
            public double calcularValorFinal() {
                return 0;
            }

            @Override
            public double valorIndividual() {
                return 0;
            }
        };
    }

    @Test
    void testCalcularValorTotal() {
        Produto produto1 = new Produto(1L, "Bahn Mi", 10.0);
        Produto produto2 = new Produto(2L, "Pho", 15.0);

        pedido.getProdutos().addAll(Arrays.asList(produto1, produto2));

        double valorTotalEsperado = 25.0; 

        assertEquals(valorTotalEsperado, pedido.calcularValorTotal());
    }
}
