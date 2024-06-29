package com.ssj5.lpm.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {
    
    private Cliente cliente1;
    private Cliente cliente2;

    @BeforeEach
    public void setUp() {
        cliente1 = new Cliente("Alice");
        cliente2 = new Cliente("Bob");
    }

    @Test
    public void testEquals() {
        // Teste para clientes iguais
        Cliente cliente1Clone = new Cliente("Alice");
        assertTrue(cliente1.equals(cliente1Clone));

        // Teste para clientes diferentes
        assertFalse(cliente1.equals(cliente2));
    }

    @Test
    public void testHashCode() {
        // Teste para hashCode consistente com equals para clientes iguais
        Cliente cliente1Clone = new Cliente("Alice");
        assertTrue(cliente1.hashCode() == cliente1Clone.hashCode());

        // Não há necessidade de comparar hashCode para clientes diferentes, apenas para consistentência
    }
}
