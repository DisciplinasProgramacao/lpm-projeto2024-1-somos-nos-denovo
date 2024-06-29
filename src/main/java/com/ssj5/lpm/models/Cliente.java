package com.ssj5.lpm.models;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Representa um cliente com um nome e um identificador único.
 */
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    /**
     * Construtor padrão da classe Cliente.
     */
    public Cliente() {
    }

    /**
     * Construtor da classe Cliente que inicializa com um nome.
     *
     * @param nome Nome do cliente.
     */
    public Cliente(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o identificador único do cliente.
     *
     * @return Identificador único do cliente.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o identificador único do cliente.
     *
     * @param id Identificador único do cliente.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o nome do cliente.
     *
     * @return Nome do cliente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do cliente.
     *
     * @param nome Nome do cliente.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Método que compara se dois objetos Cliente são iguais com base no nome.
     *
     * @param o Objeto a ser comparado.
     * @return true se os objetos são iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(nome, cliente.nome);
    }

    /**
     * Método que gera um código hash para o objeto Cliente baseado no nome.
     *
     * @return Código hash do objeto Cliente.
     */
    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
