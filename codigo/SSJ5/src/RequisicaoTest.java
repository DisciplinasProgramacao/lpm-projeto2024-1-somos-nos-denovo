package codigo.SSJ5.src;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;

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
        Cliente jogador = new Cliente("Zico");
        Mesa mesa = new Mesa(4, true);
        Requisicao requisicao = new Requisicao(4, jogador, LocalDate.now(), LocalTime.now());
        requisicao.setMesa(mesa);
        LocalTime horaSaida = requisicao.fecharRequisicao();
        assertNotNull(horaSaida);
    }

    @Test
    public void testGetHoraEntradaEquals() {
        Cliente cliente = new Cliente("Zico");
        LocalTime horaEntrada = LocalTime.now();
        Requisicao requisicao = new Requisicao(4, cliente, LocalDate.now(), horaEntrada);
        assertEquals(horaEntrada, requisicao.getHoraEntrada());
    }

    @Test
    public void testGetHoraEntradaNotEquals() {
        Cliente cliente = new Cliente("Zico");
        LocalTime horaEntrada = LocalTime.now();
        Requisicao requisicao = new Requisicao(4, cliente, LocalDate.now(), horaEntrada);
        assertNotEquals(LocalTime.of(12, 0), requisicao.getHoraEntrada());
    }

    @Test
    public void testGetHoraSaidaEquals() {
        Cliente cliente = new Cliente("Zico");
        Requisicao requisicao = new Requisicao(4, cliente, LocalDate.now(), LocalTime.now());
        requisicao.fecharRequisicao();

        LocalTime horaEsperada = LocalTime.now();
        LocalTime horaReal = requisicao.getHoraSaida();

        Duration intervalo = Duration.ofSeconds(1);

        Duration durationBetween = Duration.between(horaEsperada, horaReal).abs();

        assertTrue(durationBetween.compareTo(intervalo) <= 0);
    }

    @Test
    public void testGetHoraSaidaNotEquals() {
        Cliente cliente = new Cliente("Zico");
        Requisicao requisicao = new Requisicao(4, cliente, LocalDate.now(), LocalTime.now());
        assertNotEquals(LocalTime.of(12, 0), requisicao.getHoraSaida());
    }

    @Test
    public void testGetIdEquals() {
        Cliente cliente = new Cliente("Zico");
        Requisicao requisicao = new Requisicao(4, cliente, LocalDate.now(), LocalTime.now());
        assertNotNull(requisicao.getId());
    }

    @Test
    public void testGetIdNotEquals() {
        Cliente cliente = new Cliente("Zico");
        Requisicao requisicao1 = new Requisicao(4, cliente, LocalDate.now(), LocalTime.now());
        Requisicao requisicao2 = new Requisicao(4, cliente, LocalDate.now(), LocalTime.now());
        assertNotEquals(requisicao1.getId(), requisicao2.getId());
    }

}
