package com.codigo.ssj5.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mesa")
public class Mesa {
    private int capacidade;
    private boolean disponibilidade;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Mesa() {}

    public Mesa(int capacidade, boolean disponibilidade) {
        this.capacidade = capacidade;
        this.disponibilidade = disponibilidade;
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

    public int getId() {
        return id;
    }

    public void desocupar() {
        this.disponibilidade = true;
    }
}
