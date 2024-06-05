package com.codigo.ssj5.models;

import jakarta.persistence.Entity;

@Entity
public class PedidoFechado extends Pedido {
    private static final double TAXA = 1.1;

    public PedidoFechado() {}

    public PedidoFechado(Requisicao requisicao) {
        super(requisicao);
    }

    @Override
    public double calcularValorFinal() {
        return  MENU_FIXO_PRECO * TAXA;
    }
}
