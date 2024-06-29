package com.ssj5.lpm.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Representa um pedido fechado em um restaurante.
 */
@Entity
@DiscriminatorValue("FECHADO")
public class PedidoFechado extends Pedido {

    private static final double TAXA = 1.1;
    private static final double PRECO_FIXO = 32;

    /**
     * Calcula o valor final do pedido fechado.
     *
     * @return O valor final do pedido fechado.
     */
    @Override
    public double calcularValorFinal() {
        return PRECO_FIXO * TAXA;
    }

    /**
     * Adiciona um produto ao pedido fechado.
     *
     * @param produto O produto a ser adicionado.
     */
    @Override
    public void addProduto(Produto produto) {
        produtos.add(produto);
    }

    /**
     * Calcula o valor individual do pedido fechado.
     *
     * @return O valor individual do pedido fechado.
     */
    @Override
    public double valorIndividual() {
        return calcularValorFinal();
    }
}
