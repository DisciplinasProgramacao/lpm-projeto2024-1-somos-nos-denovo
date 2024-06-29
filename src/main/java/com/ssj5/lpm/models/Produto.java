package com.ssj5.lpm.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Produto representa um produto disponível em um restaurante.
 */
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private double preco;

    /**
     * Construtor padrão sem parâmetros.
     */
    public Produto() {
    }

    /**
     * Construtor para inicializar um produto com ID, nome e preço.
     *
     * @param id    O ID do produto.
     * @param nome  O nome do produto.
     * @param preco O preço do produto.
     */
    public Produto(Long id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    /**
     * Obtém o ID do produto.
     *
     * @return O ID do produto.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o ID do produto.
     *
     * @param id O novo ID do produto.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o nome do produto.
     *
     * @return O nome do produto.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do produto.
     *
     * @param nome O novo nome do produto.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o preço do produto.
     *
     * @return O preço do produto.
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Define o preço do produto.
     *
     * @param preco O novo preço do produto.
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }
}
