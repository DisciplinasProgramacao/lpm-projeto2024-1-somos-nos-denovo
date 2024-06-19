package codigo.SSJ5.src;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RequisicaoTest {

    private Requisicao requisicao;
    private Cliente cliente;
    private Mesa mesa;
    private Pedido pedido1;
    private Pedido pedido2;

    @BeforeEach
    public void setup() {
        cliente = new Cliente("Zico");
        requisicao = new Requisicao(4, cliente, LocalDate.now(), LocalTime.now());

        mesa = new Mesa(4, true);
        requisicao.setMesa(mesa);

        pedido1 = new PedidoAberto();
        pedido2 = new PedidoAberto();
    }

    @Test
    public void testGetQuantidadeEquals() {
        assertEquals(4, requisicao.getQuantidade());
    }

    @Test
    public void testGetQuantidadeNotEquals() {
        assertNotEquals(6, requisicao.getQuantidade());
    }

    @Test
    public void testSetQuantidadeEquals() {
        requisicao.setQuantidade(6);
        assertEquals(6, requisicao.getQuantidade());
    }

    @Test
    public void testSetQuantidadeNotEquals() {
        requisicao.setQuantidade(6);
        assertNotEquals(4, requisicao.getQuantidade());
    }

    @Test
    public void testFecharRequisicao() {
        LocalTime horaSaida = requisicao.fecharRequisicao();
        assertNotNull(horaSaida);
    }

    @Test
    public void testGetHoraEntradaEquals() {
        assertEquals(requisicao.getHoraEntrada(), requisicao.getHoraEntrada());
    }

    @Test
    public void testGetHoraEntradaNotEquals() {
        assertNotEquals(LocalTime.of(12, 0), requisicao.getHoraEntrada());
    }

    @Test
    public void testGetHoraSaidaEquals() {
        requisicao.fecharRequisicao();
        LocalTime horaEsperada = LocalTime.now();
        LocalTime horaReal = requisicao.getHoraSaida();

        Duration intervalo = Duration.ofSeconds(1);
        Duration durationBetween = Duration.between(horaEsperada, horaReal).abs();

        assertTrue(durationBetween.compareTo(intervalo) <= 0);
    }

    @Test
    public void testGetHoraSaidaNotEquals() {
        assertNotEquals(LocalTime.of(12, 0), requisicao.getHoraSaida());
    }

    @Test
    public void testGetIdEquals() {
        assertNotNull(requisicao.getId());
    }

    @Test
    public void testGetIdNotEquals() {
        Requisicao outraRequisicao = new Requisicao(4, cliente, LocalDate.now(), LocalTime.now());
        assertNotEquals(requisicao.getId(), outraRequisicao.getId());
    }

    @Test
    public void testAdicionarPedido() {
        requisicao.addPedido(pedido1);

        List<Pedido> pedidos = requisicao.getPedidos();
        assertNotNull(pedidos);
        assertEquals(1, pedidos.size());
        assertEquals(pedido1, pedidos.get(0));
    }

    @Test
    public void testCalcularValorFinal() {
        requisicao.addPedido(pedido1);

        double valorFinal = requisicao.calcularValorFinal();
        assertEquals(0.0, valorFinal);
    }

    @Test
    public void testCalcularValorPorPessoa() {
        requisicao.addPedido(pedido1);

        double valorPorPessoa = requisicao.calcularValorPorPessoa();
        assertEquals(0.0, valorPorPessoa);
    }

    @Test
    public void testGetRequisicaoInfo() {
        requisicao.addPedido(pedido1);

        String expectedInfo = "ID: 00\nCliente: Zico\nQuantidade: 04\nData: " + LocalDate.now() + "\nHora de Entrada: "
                + LocalTime.now() + "\nHora de Saída: N/A\nMesa ID: 0\n"
                + "Pedidos:\n"
                + "Itens do Pedido (Menu Aberto):\n"
                + "Preço Total: R$0,00\n";

        assertEquals(expectedInfo, requisicao.getRequisicaoInfo());
    }
}
