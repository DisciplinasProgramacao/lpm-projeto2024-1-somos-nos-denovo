package com.br.lpmssj5.ssj5.Model;

import jakarta.persistence.*;

@Entity
@Table
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduto;
    private String nomeProduto;
    private double precoProduto;

    public Produto() {
    }

    public Produto(String nomeProduto, double precoProduto) {
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public double getPrecoProduto() {
        return precoProduto;
    }

}