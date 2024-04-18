import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class RestauranteTest {

    @BeforeEach
    public void setUp(){
        Restaurante restaurante = new Restaurante(5, 4);
        Cliente cliente = new Cliente("João");
        Requisicao requisicao = new Requisicao(5, "João", 10.04, 20.00, 22.00, 9);
    }

    @Test
    void testAlocarNaMesa() {
        ArrayList<Requisicao> filaEspera = new ArrayList<>();
        filaEspera.add(requisicao); // Adiciona uma requisição à fila de espera

        int resultado = restaurante.alocarNaMesa(filaEspera, 0);
        assertEquals(1, resultado);
        assertEquals(0, filaEspera.size()); 

    }

    @Test
    void testAlocarEmMesaInvalida(){
        int resultado = restaurante.alocarNaMesa(filaEspera, 10);
        assertEquals(-1, resultado);

    }

    @Test
    void testAlocarNaMesaComFilaDeEsperaVazia(){
        
        int resultado = Restaurante.alocarNaMesa(new ArrayList<>(), 0);
        assertEquals(0, resultado);
    }

    @Test
    void testEntrarNaFilaDeEspera() {
        int tamanhoAnterior = Restaurante.getFilaDeEspera().size();
        int novoTamanho = Restaurante.entrarNaFilaDeEspera(requisicao);
        assertEquals(tamanhoAnterior + 1, novoTamanho);
        assertTrue(Restaurante.getFilaDeEspera().contains(requisicao));
    }

    @Test
    void testRemoverDaFilaDeEspera() {
        Restaurante.entrarNaFilaDeEspera(requisicao);
        int tamanhoAnterior = Restaurante.getFilaDeEspera().size();

        // Testa se a requisição é removida corretamente da fila de espera
        int novoTamanho = Restaurante.removerDaFilaDeEspera(requisicao);
        assertEquals(tamanhoAnterior - 1, novoTamanho);
    }

    @Test
    void testFecharRequisicao() {
        Restaurante.entrarNaFilaDeEspera(requisicao);
        assertTrue(Restaurante.fecharRequisicao(requisicao));
        assertFalse(Restaurante.getHistoricoRequisicao().contains(requisicao));
    }

    @Test
    void testDesocupar() {
        restaurante.entrarNaFilaDeEspera(requisicao);
        restaurante.alocarNaMesa(restaurante.getFilaDeEspera(), 0);
        assertTrue(restaurante.desocupar(requisicao));
    }

    @Test
    void testLocalizarCliente() {
        Cliente cliente = new Cliente("João");
        assertEquals(-1, restaurante.localizarCliente(cliente));
        restaurante.getMesas()[0].adicionarClientes(3);
        assertEquals(0, restaurante.localizarCliente(cliente));
    }
}