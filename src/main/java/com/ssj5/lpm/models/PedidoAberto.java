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

    @Override
    public double calcularValorFinal() {
        return calcularValorTotal() * TAXA;
    }

    @Override
    public double valorIndividual() {
        return calcularValorFinal();
    }

    @Override
    public void addProduto(Produto produto) {
        produtos.add(produto);
    }
}
