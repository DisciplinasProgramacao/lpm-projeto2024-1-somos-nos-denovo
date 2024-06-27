package com.ssj5.lpm.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Representa um pedido aberto em um restaurante.
 */
@Entity
@DiscriminatorValue("ABERTO")
public class PedidoAberto extends Pedido {

    private static final double TAXA = 1.1;

    /**
     * Calcula o valor final do pedido aberto.
     *
     * @return O valor final do pedido aberto.
     */
    @Override
    public double calcularValorFinal() {
        return calcularValorTotal() * TAXA;
    }

    /**
     * Calcula o valor individual do pedido aberto.
     *
     * @return O valor individual do pedido aberto.
     */
    @Override
    public double valorIndividual() {
        return calcularValorFinal();
    }

    /**
     * Adiciona um produto ao pedido aberto.
     *
     * @param produto O produto a ser adicionado.
     */
    @Override
    public void addProduto(Produto produto) {
        produtos.add(produto);
    }
}
