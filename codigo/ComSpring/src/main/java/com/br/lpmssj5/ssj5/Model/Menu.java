package com.br.lpmssj5.ssj5.Model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany
    private List<Produto> produtos = new ArrayList<>();

    public Menu() {}

    public Menu(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public int getId() {
        return id;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public void addProduto(Produto produto) {
        this.produtos.add(produto);
    }
}
