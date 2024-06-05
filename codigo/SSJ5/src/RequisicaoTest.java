package codigo.SSJ5.src;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RequisicaoTest {

    private Requisicao requisicao;

    @BeforeEach
    public void setUp() {
        Cliente cliente = new Cliente("Gabigol");
        requisicao = new Requisicao(3, cliente, LocalDate.now(), LocalTime.now());
    }

    @Test
    public void testGetQuantidadeEquals() {
        assertEquals(3, requisicao.getQuantidade());
    }

    @Test
    public void testGetQuantidadeNotEquals() {
        assertNotEquals(5, requisicao.getQuantidade());
    }

    @Test
    public void testGetClienteNotNull() {
        assertNotNull(requisicao.getCliente());
    }

    @Test
    public void testGetClienteNull() {
        requisicao.setCliente(null);
        assertNull(requisicao.getCliente());
    }

    @Test
    public void testGetDataNotNull() {
        assertNotNull(requisicao.getData());
    }

    @Test
    public void testGetDataNull() {
        requisicao.setData(null);
        assertNull(requisicao.getData());
    }

    @Test
    public void testGetHoraEntradaNotNull() {
        assertNotNull(requisicao.getHoraEntrada());
    }

    @Test
    public void testGetHoraEntradaNull() {
        requisicao.setHoraEntrada(null);
        assertNull(requisicao.getHoraEntrada());
    }

    @Test
    public void testGetHoraSaidaNull() {
        assertNull(requisicao.getHoraSaida());
    }

    @Test
    public void testGetHoraSaidaNotNull() {
        requisicao.fecharRequisicao();
        assertNotNull(requisicao.getHoraSaida());
    }

    @Test
    public void testGetIdNotNull() {
        assertNotNull(requisicao.getId());
    }

    @Test
    public void testGetMesaNull() {
        assertNull(requisicao.getMesa());
    }

    @Test
    public void testGetMesaNotNull() {
        Mesa mesa = new Mesa(4, true);
        requisicao.setMesa(mesa);
        assertNotNull(requisicao.getMesa());
    }

    @Test
    public void testGetPedidoNotNull() {
        assertNotNull(requisicao.getPedido());
    }

    @Test
    public void testGetPedidoNull() {
        requisicao.setPedido(null);
        assertNull(requisicao.getPedido());
    }

    @Test
    public void testSetQuantidadeEquals() {
        requisicao.setQuantidade(5);
        assertEquals(5, requisicao.getQuantidade());
    }

    @Test
    public void testSetQuantidadeNotEquals() {
        requisicao.setQuantidade(5);
        assertNotEquals(6, requisicao.getQuantidade());
    }

    @Test
    public void testSetClienteEquals() {
        Cliente cliente = new Cliente("Zico");
        requisicao.setCliente(cliente);
        assertEquals(cliente, requisicao.getCliente());
    }

    @Test
    public void testSetClienteNotEquals() {
        Cliente cliente = new Cliente("Cebolinha");
        requisicao.setCliente(cliente);
        assertNotEquals("Pedro", requisicao.getCliente());
    }

    @Test
    public void testSetDataEquals() {
        LocalDate data = LocalDate.now().minusDays(1);
        requisicao.setData(data);
        assertEquals(data, requisicao.getData());
    }

    @Test
    public void testSetDataNotEquals() {
        LocalDate data = LocalDate.now().minusDays(1);
        LocalDate outraData = LocalDate.now().minusDays(2);
        requisicao.setData(data);
        assertNotEquals(outraData, requisicao.getData());
    }

    @Test
    public void testSetHoraEntradaEquals() {
        LocalTime horaEntrada = LocalTime.now().minusHours(1);
        requisicao.setHoraEntrada(horaEntrada);
        assertEquals(horaEntrada, requisicao.getHoraEntrada());
    }

    @Test
    public void testSetHoraEntradaNotEquals() {
        LocalTime horaEntrada = LocalTime.now().minusHours(1);
        LocalTime outraHoraEntrada = LocalTime.now().minusHours(2);
        requisicao.setHoraEntrada(horaEntrada);
        assertNotEquals(outraHoraEntrada, requisicao.getHoraEntrada());
    }

    @Test
    public void testSetHoraSaidaEquals() {
        LocalTime horaSaida = LocalTime.now().minusMinutes(30);
        requisicao.setHoraSaida(horaSaida);
        assertEquals(horaSaida, requisicao.getHoraSaida());
    }

    @Test
    public void testSetHoraSaidaNotEquals() {
        LocalTime horaSaida = LocalTime.now().minusMinutes(30);
        LocalTime outraHoraSaida = LocalTime.now().minusMinutes(40);
        requisicao.setHoraSaida(horaSaida);
        assertNotEquals(outraHoraSaida, requisicao.getHoraSaida());
    }

    @Test
    public void testSetMesaEquals() {
        Mesa mesa = new Mesa(6, true);
        requisicao.setMesa(mesa);
        assertEquals(mesa, requisicao.getMesa());
    }

    @Test
    public void testSetMesaNotEquals() {
        Mesa mesa = new Mesa(6, true);
        Mesa outraMesa = new Mesa(4, true);
        requisicao.setMesa(mesa);
        assertNotEquals(outraMesa, requisicao.getMesa());
    }

    @Test
    public void testSetPedidoEquals() {
        Pedido pedido = new Pedido(requisicao);
        requisicao.setPedido(pedido);
        assertEquals(pedido, requisicao.getPedido());
    }

    @Test
    public void testSetPedidoNotEquals() {
        Pedido pedido = new Pedido(requisicao);
        Pedido outroPedido = new Pedido(requisicao);
        requisicao.setPedido(pedido);
        assertNotEquals(outroPedido, requisicao.getPedido());
    }
}