import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import java.time.LocalTime;

public class RequisicaoTest {

    private Requisicao requisicao;
    private Cliente cliente;
    private LocalDate data;
    private LocalTime horaEntrada;
    private LocalTime horaSaida;

    @Before
    public void setUp() {
        cliente = new Cliente("Cliente", 12); // Criando um cliente para a requisição
        data = LocalDate.now(); // Obtendo a data atual
        horaEntrada = LocalTime.now(); // Obtendo a hora atual como hora de entrada
        horaSaida = horaEntrada.plusHours(1); // Hora de saída uma hora após a entrada
        new Requisicao(5, cliente, LocalDate.of(2024, 4, 11), LocalTime.of(20, 0), LocalTime.of(22, 0)); // Criando a requisição
    }

    @Test
    public void testGetters() {
        assertEquals(4, requisicao.getQuantidade()); // Verificando a quantidade
        assertEquals(cliente, requisicao.getCliente()); // Verificando o cliente
        assertEquals(data, requisicao.getData()); // Verificando a data
        assertEquals(horaEntrada, requisicao.getHoraEntrada()); // Verificando a hora de entrada
        assertEquals(horaSaida, requisicao.getHoraSaida()); // Verificando a hora de saída
        assertEquals(1, requisicao.getId()); // Verificando o ID
    }

    @Test
    public void testSetters() {
        requisicao.setQuantidade(6); // Definindo uma nova quantidade
        assertEquals(6, requisicao.getQuantidade()); // Verificando se a quantidade foi alterada corretamente

        Cliente novoCliente = new Cliente("Novo Cliente", 1); // Novo cliente
        requisicao.setCliente(novoCliente); // Definindo um novo cliente
        assertEquals(novoCliente, requisicao.getCliente()); // Verificando se o cliente foi alterado corretamente

        LocalDate novaData = LocalDate.of(2024, 4, 23); // Nova data
        requisicao.setData(novaData); // Definindo uma nova data
        assertEquals(novaData, requisicao.getData()); // Verificando se a data foi alterada corretamente

        LocalTime novaHoraEntrada = LocalTime.of(14, 30); // Nova hora de entrada
        requisicao.setHoraEntrada(novaHoraEntrada); // Definindo uma nova hora de entrada
        assertEquals(novaHoraEntrada, requisicao.getHoraEntrada()); // Verificando se a hora de entrada foi alterada corretamente

        LocalTime novaHoraSaida = LocalTime.of(15, 30); // Nova hora de saída
        requisicao.setHoraSaida(novaHoraSaida); // Definindo uma nova hora de saída
        assertEquals(novaHoraSaida, requisicao.getHoraSaida()); // Verificando se a hora de saída foi alterada corretamente


    }
}
