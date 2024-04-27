import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class RestauranteV2test {

    private RestauranteV2 restaurante;
    private Cliente cliente;
    private Requisicao requisicao;
    private Mesa mesa;

    @BeforeEach
    void setUp() {
        restaurante = new RestauranteV2();
        cliente = new Cliente("Teste");
        requisicao = new Requisicao(8, cliente, LocalDate.now(), LocalTime.now(), LocalTime.now());
        restaurante.gerarRequisicao(cliente);
        mesa = new Mesa(true);
        restaurante.getMesas().add(mesa);
    }

    @Test
    void testGerarRequisicao() {
        int tamanhoAnterior = restaurante.getFilaDeEspera().size();
        restaurante.gerarRequisicao(cliente);
        assertEquals(tamanhoAnterior+1, restaurante.getFilaDeEspera().size());
    }

    @Test
    void testAlocarNaMesa() {
        Requisicao requisicao = restaurante.getFilaDeEspera().get(0);
        int mesaIndex = restaurante.alocarNaMesa(requisicao);
        assertTrue(mesaIndex >= 0);
        assertEquals(requisicao, restaurante.getMesas().get(mesaIndex).getRequisicao());
    }

    @Test
    void testDesocuparMesa() {
        assertTrue(restaurante.desocuparMesa(mesa));
        assertTrue(mesa.isDisponibilidade());
    }

    

    @Test
    void testRemoverDaFilaDeEspera() {
        restaurante.gerarRequisicao(cliente);
        assertTrue(restaurante.removerDaFilaDeEspera(requisicao));
        assertFalse(restaurante.getFilaDeEspera().contains(requisicao));
    }
    
    @Test
    void testFecharRequisicao() {
        restaurante.gerarRequisicao(cliente);
        assertTrue(restaurante.fecharRequisicao(requisicao));
        assertFalse(restaurante.getFilaDeEspera().contains(requisicao));
    }
    
    @Test
    void testLocalizarCliente() {
        restaurante.gerarRequisicao(cliente);
        assertEquals(cliente, restaurante.localizarCliente(cliente));
    }
    
    @Test
    void testLocalizarRequisicao() {
        restaurante.gerarRequisicao(cliente);
        Requisicao requisicaoEncontrada = restaurante.localizarRequisicao(requisicao);
        assertNotNull(requisicaoEncontrada);
        assertEquals(cliente, requisicaoEncontrada.getCliente());
    }
    
    @Test
    void testFecharConta() {
        restaurante.gerarRequisicao(cliente);
        restaurante.alocarNaMesa(requisicao);
        assertTrue(restaurante.fecharConta(cliente));
        assertFalse(restaurante.getRequisicoesAtendidas().contains(requisicao));
        assertTrue(restaurante.getRequisicoesFinalizadas().contains(requisicao));
    }

}
