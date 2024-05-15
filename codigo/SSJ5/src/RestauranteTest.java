package codigo.SSJ5.src;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

public class RestauranteTest {

    private Restaurante restaurante;

    @BeforeEach
    public void setUp() {
        restaurante = new Restaurante();
    }

    @Test
    public void testAlocarNaRequisicaoEquals() {
        Requisicao requisicao = restaurante.gerarRequisicao(4, "Zico");
        boolean result = restaurante.alocarNaRequisicao(requisicao);
        assertTrue(result);
    }

    @Test
    public void testAlocarNaRequisicaoNotEquals() {
        // Criar uma mesa e setar a disponibilidade para false para testar a alocação incorreta
        restaurante.getListaDeMesas().forEach(mesa -> mesa.setDisponibilidade(false));
        Requisicao requisicao = restaurante.gerarRequisicao(4, "Zico");
        boolean result = restaurante.alocarNaRequisicao(requisicao);
        assertFalse(result);
    }

    @Test
    public void testEntrarNaFilaDeEsperaEquals() {
        Requisicao requisicao = restaurante.gerarRequisicao(10, "Gabigol");
        boolean result = restaurante.entrarNaFilaDeEspera(requisicao);
        assertTrue(result);
    }

    @Test
    public void testEntrarNaFilaDeEsperaNotEquals() {
        Requisicao requisicao = restaurante.gerarRequisicao(10, "Gabigol");
        restaurante.entrarNaFilaDeEspera(requisicao);
        assertNotEquals(0, restaurante.getFilaDeEspera().size());
    }

    @Test
    public void testExibirListaDeEsperaEquals() {
        Requisicao req1 = restaurante.gerarRequisicao(6, "Pedro");
        Requisicao req2 = restaurante.gerarRequisicao(5, "Bruno Henrique");
        restaurante.entrarNaFilaDeEspera(req1);
        restaurante.entrarNaFilaDeEspera(req2);
        restaurante.exibirListaDeEspera();
        assertEquals(2, restaurante.getFilaDeEspera().size());
    }

    @Test
    public void testExibirListaDeEsperaNotEquals() {
        restaurante.exibirListaDeEspera();
        assertNotEquals(1, restaurante.getFilaDeEspera().size());
    }

    @Test
    public void testDesocuparMesaEquals() {
        Requisicao requisicao = restaurante.gerarRequisicao(4, "Leandro");
        restaurante.alocarNaRequisicao(requisicao);
        boolean result = restaurante.desocuparMesa(requisicao, requisicao.getMesa());
        assertTrue(result);
    }

    @Test
    public void testDesocuparMesaNotEquals() {
        Requisicao requisicao = restaurante.gerarRequisicao(4, "Leandro");
        restaurante.alocarNaRequisicao(requisicao);
        boolean result = restaurante.desocuparMesa(requisicao, requisicao.getMesa());
        assertNotEquals(false, result);
    }

    @Test
    public void testFecharContaEquals() {
        Requisicao requisicao = restaurante.gerarRequisicao(4, "Gabigol");
        restaurante.alocarNaRequisicao(requisicao);
        boolean result = restaurante.fecharConta(requisicao);
        assertTrue(result);
    }

    @Test
    public void testFecharContaNotEquals() {
        Requisicao requisicao = restaurante.gerarRequisicao(4, "Gabigol");
        boolean result = restaurante.fecharConta(requisicao);
        assertNotEquals(false, result);
    }

    @Test
    public void testGerarRequisicaoEquals() {
        Requisicao requisicao = restaurante.gerarRequisicao(4, "Diego");
        assertEquals(4, requisicao.getQuantidade());
        assertEquals("Diego", requisicao.getCliente().getNome());
    }

    @Test
    public void testGerarRequisicaoNotEquals() {
        Requisicao requisicao = restaurante.gerarRequisicao(4, "Diego");
        assertNotEquals(5, requisicao.getQuantidade());
        assertNotEquals("Leandro", requisicao.getCliente().getNome());
    }
}
