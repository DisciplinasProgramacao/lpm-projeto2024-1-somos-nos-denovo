import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RestauranteTest {

    private Restaurante restaurante;

    @BeforeEach
    public void setUp() {
        restaurante = new Restaurante(5, 4); // Inicializa o restaurante com 5 mesas, cada uma com capacidade para 4 pessoas
    }

    @Test
    public void testAlocarNaMesa() {
        Requisicao requisicao = new Requisicao(3, new Cliente("Cliente Teste"), null, null, null, 1);
        int result = restaurante.alocarNaMesa(requisicao, 0); // Aloca na primeira mesa (índice 0)

        assertEquals(1, result); // Espera-se que a alocação seja bem-sucedida
    }

    @Test
    public void testEntrarNaFilaDeEspera() {
        Requisicao requisicao = new Requisicao(3, new Cliente("Cliente Teste"), null, null, null, 1);
        int tamanhoAntes = restaurante.entrarNaFilaDeEspera(requisicao); // Tamanho da fila antes de adicionar a requisição
        int tamanhoDepois = restaurante.entrarNaFilaDeEspera(requisicao); // Tamanho da fila após adicionar a requisição novamente

        assertEquals(1, tamanhoDepois - tamanhoAntes); // Espera-se que o tamanho da fila aumente em 1 após adicionar uma requisição
    }

    @Test
    public void testRemoverDaFilaDeEspera() {
        Requisicao requisicao = new Requisicao(3, new Cliente("Cliente Teste"), null, null, null, 1);
        restaurante.entrarNaFilaDeEspera(requisicao); // Adiciona uma requisição à fila de espera
        int tamanhoAntes = restaurante.removerDaFilaDeEspera(requisicao); // Tamanho da fila antes de remover a requisição
        int tamanhoDepois = restaurante.removerDaFilaDeEspera(requisicao); // Tamanho da fila após remover a requisição novamente

        assertEquals(1, tamanhoAntes - tamanhoDepois); // Espera-se que o tamanho da fila diminua em 1 após remover uma requisição
    }

    @Test
    public void testFecharRequisicao() {
        Requisicao requisicao = new Requisicao(3, new Cliente("Cliente Teste"), null, null, null, 1);
        restaurante.entrarNaFilaDeEspera(requisicao); // Adiciona uma requisição à fila de espera
        boolean result = restaurante.fecharRequisicao(requisicao); // Fecha a requisição

        assertTrue(result); // Espera-se que a requisição seja fechada com sucesso
    }

    @Test
    public void testDesocupar() {
        Requisicao requisicao = new Requisicao(3, new Cliente("Cliente Teste"), null, null, null, 1);
        restaurante.alocarNaMesa(requisicao, 0); // Aloca a requisição em uma mesa
        boolean result = restaurante.desocupar(requisicao); // Desocupa a mesa associada à requisição

        assertTrue(result); // Espera-se que a mesa seja desocupada com sucesso
    }

    @Test
    public void testLocalizarCliente() {
        Cliente cliente = new Cliente("Cliente Teste");
        Requisicao requisicao = new Requisicao(3, cliente, null, null, null, 1);
        restaurante.alocarNaMesa(requisicao, 0); // Aloca a requisição em uma mesa

        int index = restaurante.localizarCliente(cliente); // Localiza o cliente no restaurante

        assertEquals(0, index); // Espera-se que o cliente seja encontrado na primeira mesa
    }

    @Test
    public void testLocalizarRequisicao() {
        Requisicao requisicao = new Requisicao(3, new Cliente("Cliente Teste"), null, null, null, 1);
        restaurante.alocarNaMesa(requisicao, 0); // Aloca a requisição em uma mesa

        int index = restaurante.localizarRequisicao(requisicao); // Localiza a requisição no restaurante

        assertEquals(0, index); // Espera-se que a requisição seja encontrada na primeira mesa
    }
}
