package com.ssj5.lpm.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class RequisicaoTest {

    private Requisicao requisicao;
    private Cliente cliente;
    private Mesa mesa;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("Zico");
        LocalDateTime dataHora = LocalDateTime.now();
        int quantidade = 4;
        requisicao = new Requisicao(cliente, dataHora, quantidade);

        mesa = new Mesa(4, true);
        requisicao.setMesa(mesa);
    }

    @Test
    void testGetId() {
        assertNull(requisicao.getId());
    }

    @Test
    void testGetCliente() {
        assertEquals(cliente, requisicao.getCliente());
    }

    @Test
    void testGetDataHora() {
        assertNotNull(requisicao.getDataHora());
    }

    @Test
    void testGetQuantidade() {
        assertEquals(4, requisicao.getQuantidade());
    }

    @Test
    void testIsFoiAtendida() {
        assertFalse(requisicao.isFoiAtendida());
    }

    @Test
    void testGetMesa() {
        assertEquals(mesa, requisicao.getMesa());
    }

    @Test
    void testSetPedido() {
        Pedido pedido = new PedidoAberto();
        requisicao.setPedido(pedido);
        assertEquals(pedido, requisicao.getPedido());
    }

    @Test
    void testFecharRequisicao() {
        requisicao.fecharRequisicao();
        assertTrue(requisicao.isFoiAtendida());
        assertTrue(mesa.isDisponibilidade());
    }

    @Test
    void testGetRequisicaoInfo() {
        String requisicaoInfo = requisicao.getRequisicaoInfo();
        assertNotNull(requisicaoInfo);
        System.out.println(requisicaoInfo);
    }

    @Test
    void testGetHoraSaida() {
        assertNull(requisicao.getHoraSaida());
        requisicao.fecharRequisicao();
        assertNotNull(requisicao.getHoraSaida());
    }

    @Test
    void testGetPedidos() {
        assertNotNull(requisicao.getPedidos());
    }

    @Test
    void testValorConta() {
        Pedido pedido = new PedidoAberto();
        Produto produto1 = new Produto(1L, "Produto 1", 10.0);
        Produto produto2 = new Produto(2L, "Produto 2", 15.0);
        pedido.addProduto(produto1);
        pedido.addProduto(produto2);
        requisicao.setPedido(pedido);
        double valorConta = requisicao.valorConta();
        assertEquals(25.0, valorConta);
    }

    @Test
    void testInfoConta() {
        Pedido pedido = new PedidoAberto();
        Produto produto1 = new Produto(1L, "Produto 1", 10.0);
        Produto produto2 = new Produto(2L, "Produto 2", 15.0);
        pedido.addProduto(produto1);
        pedido.addProduto(produto2);
        requisicao.setPedido(pedido);
        String infoConta = requisicao.infoConta();
        assertEquals("Valor Total: 25.0, Valor por pessoa: 6.25", infoConta);
    }
}
