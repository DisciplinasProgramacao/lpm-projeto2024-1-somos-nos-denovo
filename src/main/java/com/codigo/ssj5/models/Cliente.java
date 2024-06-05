package com.codigo.ssj5.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente {
    private String nome;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Cliente() {}

    public Cliente(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object cli) {
        if (this == cli) return true;
        if (cli == null || getClass() != cli.getClass()) return false;
        Cliente cliente = (Cliente) cli;
        return nome.equals(cliente.nome);
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }
}
