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

    @Override
    public double calcularValorFinal() {
        return PRECO_FIXO * TAXA;
    }
    //Alterar de acordo
    @Override
    public void addProduto(Produto produto) {
        produtos.add(produto);
    }
    
      @Override
      public double valorIndividual() {
          return calcularValorFinal();
      }
}