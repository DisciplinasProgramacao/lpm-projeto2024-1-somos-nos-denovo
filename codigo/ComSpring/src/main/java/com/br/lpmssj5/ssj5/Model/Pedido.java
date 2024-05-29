package com.br.lpmssj5.ssj5.Model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double valorTotal;

    @ManyToMany
    private List<Produto> produtos = new ArrayList<>();

    public Pedido() {}

    public int getId() {
        return id;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
        calcularValorTotal();
    }

    public void addProdutos(List<Produto> produtos) {
        this.produtos.addAll(produtos);
        calcularValorTotal();
    }

    public double calcularValorTotal() {
        valorTotal = produtos.stream().mapToDouble(Produto::getPrecoProduto).sum();
        return valorTotal;
    }

    public double calcularValorFinal() {
        return calcularValorTotal() * 1.1;
    }
}
