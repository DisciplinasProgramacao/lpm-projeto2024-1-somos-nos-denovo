package codigo.SSJ5.src;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RequisicaoTest {

    @Test
    public void testGetQuantidadeEquals() {
        Cliente cliente = new Cliente("Zico");
        Requisicao requisicao = new Requisicao(4, cliente, LocalDate.now(), LocalTime.now());
        assertEquals(4, requisicao.getQuantidade());
    }

    @Test
    public void testGetQuantidadeNotEquals() {
        Cliente cliente = new Cliente("Zico");
        Requisicao requisicao = new Requisicao(4, cliente, LocalDate.now(), LocalTime.now());
        assertNotEquals(6, requisicao.getQuantidade());
    }

    @Test
    public void testSetQuantidadeEquals() {
        Cliente cliente = new Cliente("Zico");
        Requisicao requisicao = new Requisicao(4, cliente, LocalDate.now(), LocalTime.now());
        requisicao.setQuantidade(6);
        assertEquals(6, requisicao.getQuantidade());
    }

    @Test
    public void testSetQuantidadeNotEquals() {
        Cliente cliente = new Cliente("Zico");
        Requisicao requisicao = new Requisicao(4, cliente, LocalDate.now(), LocalTime.now());
        requisicao.setQuantidade(6);
        assertNotEquals(4, requisicao.getQuantidade());
    }

    @Test
public void testFecharRequisicao() {
    Restaurante restaurante = new Restaurante();
    Cliente jogador = new Cliente("Zico");
    Mesa mesa = new Mesa(4, true); 
    Requisicao requisicao = new Requisicao(4, jogador, LocalDate.now(), LocalTime.now());
    requisicao.setMesa(mesa); 
    List<Requisicao> historicoRequisicao = new ArrayList<>();
    LocalTime horaSaida = requisicao.fecharRequisicao(historicoRequisicao);
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
}
