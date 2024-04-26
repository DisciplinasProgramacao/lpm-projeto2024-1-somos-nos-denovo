import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import java.time.LocalTime;

public class RestauranteTest {

    private Restaurante restaurante;
    private Requisicao requisicao1, requisicao2;
    private Cliente cliente1, cliente2;

    @Before
    public void setUp() {
        restaurante = new Restaurante(5, 4); // 5 mesas com capacidade para 4 pessoas cada
        cliente1 = new Cliente("Cliente1", 1); // Criando clientes
        cliente2 = new Cliente("Cliente2", 2);
        requisicao1 = new Requisicao(5, cliente1, LocalDate.of(2024, 4, 10), LocalTime.of(20, 0), LocalTime.of(22, 0)); // Criando requisições
        requisicao2 = new Requisicao(5, cliente2, LocalDate.of(2024, 4, 11), LocalTime.of(20, 0), LocalTime.of(22, 0));
    }

    @Test
    public void testAlocarNaMesa() {
        assertEquals(1, restaurante.alocarNaMesa(requisicao1, 0)); // Teste de alocação bem-sucedida
        assertEquals(0, restaurante.alocarNaMesa(requisicao2, 1)); // Teste de fila de espera vazia
        assertEquals(-1, restaurante.alocarNaMesa(null, 3)); // Teste de requisição nula
        assertEquals(-1, restaurante.alocarNaMesa(requisicao2, 10)); // Teste de número de mesa inválido
    }

    @Test
    public void testEntrarNaFilaDeEspera() {
        assertEquals(1, restaurante.entrarNaFilaDeEspera(requisicao2)); // Teste de adição bem-sucedida
        assertEquals(2, restaurante.entrarNaFilaDeEspera(requisicao1)); // Teste de adição bem-sucedida
    }

    @Test
    public void testRemoverDaFilaDeEspera() {
        restaurante.entrarNaFilaDeEspera(requisicao1);
        assertEquals(1, restaurante.removerDaFilaDeEspera(requisicao1)); // Teste de remoção bem-sucedida
        assertEquals(0, restaurante.removerDaFilaDeEspera(requisicao2)); // Teste de remoção bem-sucedida
    }

    @Test
    public void testFecharRequisicao() {
        restaurante.alocarNaMesa(requisicao1, 0); // Adicionando uma requisição
        assertTrue(restaurante.fecharRequisicao(requisicao1)); // Teste de fechamento bem-sucedido
        assertFalse(restaurante.fecharRequisicao(requisicao2)); // Teste de requisição não encontrada
    }

    @Test
    public void testDesocupar() {
        restaurante.alocarNaMesa(requisicao1, 0); // Adicionando uma requisição
        assertTrue(restaurante.desocupar(requisicao1)); // Teste de desocupação bem-sucedida
        assertFalse(restaurante.desocupar(requisicao2)); // Teste de requisição não encontrada
        assertFalse(restaurante.desocupar(null)); // Teste de requisição nula
    }

    @Test
    public void testLocalizarCliente() {
        restaurante.alocarNaMesa(requisicao1, 0); // Adicionando uma requisição
        assertEquals(0, restaurante.localizarCliente(cliente1)); // Teste de localização bem-sucedida
        assertEquals(-1, restaurante.localizarCliente(cliente2)); // Teste de cliente não encontrado
    }

    @Test
    public void testLocalizarRequisicao() {
        restaurante.alocarNaMesa(requisicao1, 0); // Adicionando uma requisição
        assertEquals(0, restaurante.localizarRequisicao(requisicao1)); // Teste de localização bem-sucedida
        assertEquals(-1, restaurante.localizarRequisicao(requisicao2)); // Teste de requisição não encontrada
    }
}
