import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RequisicaoTest {

    private Mesa[] mesas;

    @BeforeEach
    public void setUp() {
        // Configuração inicial das mesas
        mesas = new Mesa[1];
        mesas[0] = new Mesa(4); // Uma mesa com capacidade para 4 pessoas
    }

    @Test
    void testFecharConta_sucesso() {
        Requisicao requisicao = new Requisicao(3, null, null, LocalTime.of(12, 0), null, 1);

        LocalTime horaSaida = requisicao.fecharConta(mesas);

        // Testa se a hora de saída é igual a 1 hora após a hora de entrada
        assertEquals(LocalTime.of(13, 0), horaSaida);
    }

    @Test
    void testFecharConta_semMesaAssociada() {
        Requisicao requisicao = new Requisicao(3, null, null, null, null, 1);

        // A requisição não está associada a nenhuma mesa, então espera-se que retorne null
        LocalTime horaSaida = requisicao.fecharConta(mesas);

        assertNull(horaSaida);
    }

    @Test
    void testFecharConta_comMesaCheia() {
        // Preenche a mesa com clientes
        mesas[0].adicionarClientes(4);

        Requisicao requisicao = new Requisicao(3, null, null, LocalTime.of(12, 0), null, 1);

        // Como a mesa está cheia, não deve ser possível fechar a conta
        LocalTime horaSaida = requisicao.fecharConta(mesas);

        assertNull(horaSaida);
    }
}
