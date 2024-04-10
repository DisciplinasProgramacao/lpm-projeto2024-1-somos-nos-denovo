import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class RestauranteTest {

    @BeforeEach
    public void setUp(){
        restaurante = new Restaurante(5, 4);
        requisicao = new Requisicao(5);
    }

    @Test
    void testAlocarNaMesa() {
        ArrayList<Requisicao> filaEspera = new ArrayList<>();
        filaEspera.add(requisicao); // Adiciona uma requisição à fila de espera

        // Testa a alocação em uma mesa válida
        int resultado = restaurante.alocarNaMesa(filaEspera, 0);
        assertEquals(1, resultado);
        assertEquals(0, filaEspera.size()); // Verifica se a requisição foi removida da fila de espera

        // Testa a alocação em uma mesa inválida
        resultado = restaurante.alocarNaMesa(filaEspera, 10);
        assertEquals(-1, resultado);

        // Testa a alocação com fila de espera vazia
        resultado = restaurante.alocarNaMesa(new ArrayList<>(), 0);
        assertEquals(0, resultado);
    }

    @Test
    void testEntrarNaFilaDeEspera() {
        int tamanhoAnterior = restaurante.getFilaDeEspera().size();

        // Testa se a requisição é adicionada corretamente à fila de espera
        int novoTamanho = restaurante.entrarNaFilaDeEspera(requisicao);
        assertEquals(tamanhoAnterior + 1, novoTamanho);
        assertTrue(restaurante.getFilaDeEspera().contains(requisicao));
    }

    @Test
    void testRemoverDaFilaDeEspera() {
        restaurante.entrarNaFilaDeEspera(requisicao);
        int tamanhoAnterior = restaurante.getFilaDeEspera().size();

        // Testa se a requisição é removida corretamente da fila de espera
        int novoTamanho = restaurante.removerDaFilaDeEspera(requisicao);
        assertEquals(tamanhoAnterior - 1, novoTamanho);
    }
}
