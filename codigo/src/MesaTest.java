import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MesaTest {

    Mesa mesa;

    @BeforeEach
    void setUp() {
        mesa = new Mesa(4, 1); // Criando um novo objeto Mesa com capacidade de 4 e ID de 1 antes de cada caso de teste
    }

    @Test
    void testOcupar() {
        // Testando o método ocupar
        Requisicao requisicao = new Requisicao();
        assertTrue(mesa.ocupar(requisicao)); // A mesa deve ser ocupada com sucesso
        assertFalse(mesa.ocupar(requisicao)); // A mesa já está ocupada, então deve falhar
    }

    @Test
    void testDesocupar() {
        // Testando o método desocupar
        Requisicao requisicao = new Requisicao();
        mesa.ocupar(requisicao); // Primeiro, ocupamos a mesa
        assertTrue(mesa.desocupar(requisicao)); // Agora, devemos ser capazes de desocupar a mesa
        assertFalse(mesa.desocupar(requisicao)); // A mesa já está desocupada, então deve falhar
    }
}
