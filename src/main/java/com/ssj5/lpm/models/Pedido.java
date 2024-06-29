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
 * Classe abstrata que representa um pedido em um restaurante.
 * Define comportamentos e atributos comuns para todos os tipos de pedidos.
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

    /**
     * Obtém o ID do pedido.
     *
     * @return O ID do pedido.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o ID do pedido.
     *
     * @param id O ID do pedido.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém a lista de produtos do pedido.
     *
     * @return A lista de produtos do pedido.
     */
    public List<Produto> getProdutos() {
        return produtos;
    }

    /**
     * Adiciona um produto ao pedido (método abstrato a ser implementado nas subclasses).
     *
     * @param produto O produto a ser adicionado ao pedido.
     */
    public abstract void addProduto(Produto produto);

    /**
     * Calcula o valor total do pedido com base nos produtos.
     *
     * @return O valor total do pedido.
     */
    public double calcularValorTotal() {
        return produtos.stream().mapToDouble(Produto::getPreco).sum();
    }

    /**
     * Calcula o valor final do pedido (método abstrato a ser implementado nas subclasses).
     *
     * @return O valor final do pedido.
     */
    public abstract double calcularValorFinal();

    /**
     * Calcula o valor individual por pessoa do pedido (método abstrato a ser implementado nas subclasses).
     *
     * @return O valor individual por pessoa do pedido.
     */
    public abstract double valorIndividual();

    /**
     * Formata a representação textual do pedido, incluindo itens, preço total e preço por pessoa.
     *
     * @return A representação formatada do pedido.
     */
    public String formatPedido() {
        StringBuilder sb = new StringBuilder();
        sb.append("Itens do Pedido:\n");
        produtos.forEach(p -> sb.append(String.format(" - %s: R$%.2f\n", p.getNome(), p.getPreco())));
        sb.append(String.format("Preço Total: R$%.2f\n", calcularValorFinal()));
        sb.append(String.format("Preço por pessoa: R$%.2f\n", valorIndividual()));
        return sb.toString();
    }
}
