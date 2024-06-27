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

    public Mesa() {
    }

    public Mesa(int capacidade, boolean disponibilidade) {
        this.capacidade = capacidade;
        this.disponibilidade = disponibilidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public void desocupar() {
        this.disponibilidade = true;
    }
    
}
