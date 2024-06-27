package com.ssj5.lpm.models;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ProdutoTest {

    private Produto produto;

    @BeforeEach
    void setUp() {
        produto = new Produto(1L, "Bahn Mi", 10.0);
    }

    @Test
    void testGetId() {
        assertEquals(1L, produto.getId());
    }

    @Test
    void testSetId() {
        produto.setId(2L);
        assertEquals(2L, produto.getId());
    }

    @Test
    void testGetNome() {
        assertEquals("Bahn Mi", produto.getNome());
    }

    @Test
    void testSetNome() {
        produto.setNome("Pho");
        assertEquals("Pho", produto.getNome());
    }

    @Test
    void testGetPreco() {
        assertEquals(10.0, produto.getPreco());
    }

    @Test
    void testSetPreco() {
        produto.setPreco(15.0);
        assertEquals(15.0, produto.getPreco());
    }
}
