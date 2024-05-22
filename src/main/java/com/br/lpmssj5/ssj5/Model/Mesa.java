package com.br.lpmssj5.ssj5.Model;

import jakarta.persistence.*;

@Entity
@Table
public class Mesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int capacidade;
    private boolean disponibilidade = true;

    public Mesa() {}

    public Mesa(int capacidade) {
        this.capacidade = capacidade;
    }

    public int getId() {
        return id;
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
}
