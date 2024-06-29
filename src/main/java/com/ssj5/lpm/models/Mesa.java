package com.ssj5.lpm.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Representa uma mesa em um restaurante, com capacidade e disponibilidade.
 */
@Entity
public class Mesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int capacidade;
    private boolean disponibilidade;

    /**
     * Construtor padrão da classe Mesa.
     */
    public Mesa() {
    }

    /**
     * Construtor da classe Mesa para inicializar com capacidade e disponibilidade.
     *
     * @param capacidade     A capacidade da mesa (quantidade de lugares).
     * @param disponibilidade A disponibilidade da mesa (true se disponível, false se ocupada).
     */
    public Mesa(int capacidade, boolean disponibilidade) {
        this.capacidade = capacidade;
        this.disponibilidade = disponibilidade;
    }

    /**
     * Obtém o ID da mesa.
     *
     * @return O ID da mesa.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o ID da mesa.
     *
     * @param id O ID da mesa.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém a capacidade da mesa (quantidade de lugares).
     *
     * @return A capacidade da mesa.
     */
    public int getCapacidade() {
        return capacidade;
    }

    /**
     * Define a capacidade da mesa (quantidade de lugares).
     *
     * @param capacidade A capacidade da mesa.
     */
    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    /**
     * Verifica a disponibilidade da mesa.
     *
     * @return true se a mesa está disponível, false caso contrário.
     */
    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    /**
     * Define a disponibilidade da mesa.
     *
     * @param disponibilidade true se a mesa está disponível, false caso contrário.
     */
    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    /**
     * Método para marcar a mesa como disponível.
     */
    public void desocupar() {
        this.disponibilidade = true;
    }
}
