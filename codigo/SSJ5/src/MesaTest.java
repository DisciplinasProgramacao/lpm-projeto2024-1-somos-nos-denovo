import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MesaTest {

    Mesa mesa;

    @BeforeEach
    void setUp() {
        mesa = new Mesa(4, true); // Criando um novo objeto Mesa com capacidade de 4 e ID de 1 antes de cada caso de teste
    }
    
}
