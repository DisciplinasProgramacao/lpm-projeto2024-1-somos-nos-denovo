package com.ssj5.lpm.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;

/**
 * Classe abstrata para a entidade Pedido.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_pedido", discriminatorType = DiscriminatorType.STRING)
public abstract class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "pedido_produtos",
        joinColumns = @JoinColumn(name = "pedido_id"),
        inverseJoinColumns = @JoinColumn(name = "produtos_id")
    )
protected List<Produto> produtos = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public abstract void addProduto(Produto produto);

    public double calcularValorTotal() {
        return produtos.stream().mapToDouble(Produto::getPreco).sum();
    }

    public abstract double calcularValorFinal();

    public abstract double valorIndividual();

    public String formatPedido() {
        StringBuilder sb = new StringBuilder();
        sb.append("Itens do Pedido:\n");
        produtos.forEach(p -> sb.append(String.format(" - %s: R$%.2f\n", p.getNome(), p.getPreco())));
        sb.append(String.format("Preço Total: R$%.2f\n", calcularValorFinal()));
        sb.append(String.format("Preço por pessoa: R$%.2f\n", valorIndividual()));
        return sb.toString();
    }
}