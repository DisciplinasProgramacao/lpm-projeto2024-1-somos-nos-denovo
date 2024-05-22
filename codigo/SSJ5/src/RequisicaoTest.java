package codigo.SSJ5.src;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RequisicaoTest {

    @Test
    public void testGetQuantidadeEquals() {
        Cliente cliente = new Cliente("Zico");
        Requisicao requisicao = new Requisicao(4, cliente, LocalDate.now(), LocalTime.now(), null, null);
        assertEquals(4, requisicao.getQuantidade());
    }

    @Test
    public void testGetQuantidadeNotEquals() {
        Cliente cliente = new Cliente("Zico");
        Requisicao requisicao = new Requisicao(4, cliente, LocalDate.now(), LocalTime.now(), null, null);
        assertNotEquals(6, requisicao.getQuantidade());
    }

    @Test
    public void testSetQuantidadeEquals() {
        Cliente cliente = new Cliente("Zico");
        Requisicao requisicao = new Requisicao(4, cliente, LocalDate.now(), LocalTime.now(), null, null);
        requisicao.setQuantidade(6);
        assertEquals(6, requisicao.getQuantidade());
    }

    @Test
    public void testSetQuantidadeNotEquals() {
        Cliente cliente = new Cliente("Zico");
        Requisicao requisicao = new Requisicao(4, cliente, LocalDate.now(), LocalTime.now(), null, null);
        requisicao.setQuantidade(6);
        assertNotEquals(4, requisicao.getQuantidade());
    }

    @Test
public void testFecharRequisicao() {
    Restaurante restaurante = new Restaurante();
    Cliente jogador = new Cliente("Zico");
    Mesa mesa = new Mesa(4, true); 
    Requisicao requisicao = new Requisicao(4, jogador, LocalDate.now(), LocalTime.now(), null, restaurante);
    requisicao.setMesa(mesa); 
    List<Requisicao> historicoRequisicao = new ArrayList<>();
    LocalTime horaSaida = requisicao.fecharRequisicao(requisicao, historicoRequisicao);
    assertNotNull(horaSaida);
}


    @Test
    public void testGetHoraEntradaEquals() {
        Cliente cliente = new Cliente("Zico");
        LocalTime horaEntrada = LocalTime.now();
        Requisicao requisicao = new Requisicao(4, cliente, LocalDate.now(), horaEntrada, null, null);
        assertEquals(horaEntrada, requisicao.getHoraEntrada());
    }

    @Test
    public void testGetHoraEntradaNotEquals() {
        Cliente cliente = new Cliente("Zico");
        LocalTime horaEntrada = LocalTime.now();
        Requisicao requisicao = new Requisicao(4, cliente, LocalDate.now(), horaEntrada, null, null);
        assertNotEquals(LocalTime.of(12, 0), requisicao.getHoraEntrada());
    }

    @Test
    public void testGetHoraSaidaEquals() {
        Cliente cliente = new Cliente("Zico");
        LocalTime horaSaida = LocalTime.now();
        Requisicao requisicao = new Requisicao(4, cliente, LocalDate.now(), LocalTime.now(), horaSaida, null);
        assertEquals(horaSaida, requisicao.getHoraSaida());
    }

    @Test
    public void testGetHoraSaidaNotEquals() {
        Cliente cliente = new Cliente("Zico");
        LocalTime horaSaida = LocalTime.now();
        Requisicao requisicao = new Requisicao(4, cliente, LocalDate.now(), LocalTime.now(), horaSaida, null);
        assertNotEquals(LocalTime.of(12, 0), requisicao.getHoraSaida());
    }

    @Test
    public void testGetIdEquals() {
        Cliente cliente = new Cliente("Zico");
        Requisicao requisicao = new Requisicao(4, cliente, LocalDate.now(), LocalTime.now(), null, null);
        assertNotNull(requisicao.getId());
    }

    @Test
    public void testGetIdNotEquals() {
        Cliente cliente = new Cliente("Zico");
        Requisicao requisicao1 = new Requisicao(4, cliente, LocalDate.now(), LocalTime.now(), null, null);
        Requisicao requisicao2 = new Requisicao(4, cliente, LocalDate.now(), LocalTime.now(), null, null);
        assertNotEquals(requisicao1.getId(), requisicao2.getId());
    }

    @Test
    public void testIsStatusEquals() {
        Cliente cliente = new Cliente("Zico");
        Requisicao requisicao = new Requisicao(4, cliente, LocalDate.now(), LocalTime.now(), null, null);
        assertTrue(requisicao.isStatus());
    }

    @Test
    public void testIsStatusNotEquals() {
        Cliente cliente = new Cliente("Zico");
        Requisicao requisicao = new Requisicao(4, cliente, LocalDate.now(), LocalTime.now(), null, null);
        requisicao.setStatus(false);
        assertFalse(requisicao.isStatus());
    }

    @Test
    public void testExibirHistoricoDeRequisicoesVazio() {
        Restaurante restaurante = new Restaurante();
        Requisicao requisicao = new Requisicao(4, new Cliente("Zico"), LocalDate.now(), LocalTime.now(), null, restaurante);
        String resultado = requisicao.exibirHistoricoDeRequisicoes();
        assertEquals("Não há requisições no histórico.", resultado);
    }

    @Test
    public void testExibirHistoricoDeRequisicoesComDados() {
        Restaurante restaurante = new Restaurante();
        Cliente cliente = new Cliente("Zico");
        Requisicao requisicao = new Requisicao(4, cliente, LocalDate.now(), LocalTime.now(), null, restaurante);
        restaurante.getHistoricoDeRequisicao().add(requisicao);
        String resultado = requisicao.exibirHistoricoDeRequisicoes();
        assertTrue(resultado.contains("Histórico de Requisições:"));
        assertTrue(resultado.contains("ID: " + requisicao.getId()));
        assertTrue(resultado.contains("Cliente: " + cliente.getNome()));
        assertTrue(resultado.contains("Quantidade: " + requisicao.getQuantidadePessoas()));
        assertTrue(resultado.contains("Data: " + requisicao.getData()));
        assertTrue(resultado.contains("Hora de Entrada: " + requisicao.getHoraEntrada()));
    }

    @Test
    public void testAdicionarPedidoNovo() {
        Requisicao requisicao = new Requisicao(4, new Cliente("Zico"), LocalDate.now(), LocalTime.now(), null, null);
        List<Produto> produtos = Arrays.asList(new Produto("Produto1", 10.0), new Produto("Produto2", 15.0));
        requisicao.adicionarPedido(produtos);

        assertNotNull(requisicao.getPedido());
        assertEquals(2, requisicao.getPedido().getProdutos().size());
    }

    @Test
    public void testAdicionarPedidoExistente() {
        Requisicao requisicao = new Requisicao(4, new Cliente("Zico"), LocalDate.now(), LocalTime.now(), null, null);
        List<Produto> produtos1 = Arrays.asList(new Produto("Produto1", 10.0));
        List<Produto> produtos2 = Arrays.asList(new Produto("Produto2", 15.0));
        requisicao.adicionarPedido(produtos1);
        requisicao.adicionarPedido(produtos2);

        assertNotNull(requisicao.getPedido());
        assertEquals(2, requisicao.getPedido().getProdutos().size());
    }

}
