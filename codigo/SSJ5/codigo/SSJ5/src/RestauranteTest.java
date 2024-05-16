package codigo.SSJ5.src;

import org.junit.jupiter.api.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

public class RestauranteTest {

    private Restaurante restaurante;

    @BeforeEach
    public void setUp() {
        restaurante = new Restaurante();
    }

    @Test
    public void testAlocarMesa() {
        Requisicao requisicao = restaurante.gerarRequisicao(4, new Cliente("João"), LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(2));
        assertTrue(restaurante.alocarNaRequisicao(requisicao));
    }

    @Test
    public void testEntrarFilaEspera() {
        Requisicao requisicao = restaurante.gerarRequisicao(8, new Cliente("Maria"), LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(2));
        assertTrue(restaurante.entrarFilaEspera(requisicao));
    }

    @Test
    public void testExibirListaEspera() {
        Requisicao req1 = restaurante.gerarRequisicao(6, new Cliente("Pedro"), LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(2));
        Requisicao req2 = restaurante.gerarRequisicao(5, new Cliente("Ana"), LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(2));
        restaurante.entrarFilaEspera(req1);
        restaurante.entrarFilaEspera(req2);

        assertDoesNotThrow(() -> restaurante.exibirListaEspera());
    }

    @Test
    public void testRemoverFilaEspera() {
        Requisicao requisicao = restaurante.gerarRequisicao(3, new Cliente("Carlos"), LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(2));
        restaurante.entrarFilaEspera(requisicao);
        assertTrue(restaurante.removerFilaEspera(requisicao));
    }

    @Test
    public void testDesocuparMesa() {
        Requisicao requisicao = restaurante.gerarRequisicao(2, new Cliente("Laura"), LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(2));
        restaurante.alocarNaRequisicao(requisicao);
        assertTrue(restaurante.desocuparMesa(restaurante.listaDeMesas.get(0)));
    }

    @Test
    public void testFecharConta() {
        Requisicao requisicao = restaurante.gerarRequisicao(4, new Cliente("Mariana"), LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(2));
        restaurante.alocarNaRequisicao(requisicao);
        assertTrue(restaurante.fecharConta(requisicao));
    }
}

