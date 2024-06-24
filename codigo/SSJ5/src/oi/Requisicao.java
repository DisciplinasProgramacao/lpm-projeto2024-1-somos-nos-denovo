package codigo.SSJ5.src.oi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Requisicao representa uma requisição feita por um cliente em um restaurante.
 * Contém informações como cliente, quantidade de pessoas, data e horário de entrada e saída,
 * além de detalhes sobre a mesa ocupada e o pedido realizado.
 */
public class Requisicao {
    private boolean foiAtendida;
    private int quantidade;
    private Cliente cliente;
    private LocalDate data;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;
    private static int nextId = 0;
    private int id;
    private IPedido pedido;
    private Mesa mesa;

    /**
     * Construtor da classe Requisicao.
     * Inicializa a requisição com a quantidade de pessoas, cliente, data e hora de entrada especificados.
     * O ID da requisição é automaticamente atribuído.
     *
     * @param quantidade   A quantidade de pessoas na requisição.
     * @param cliente      O cliente associado à requisição.
     * @param data         A data da requisição.
     * @param horaEntrada  A hora de entrada da requisição.
     */
    public Requisicao(int quantidade, Cliente cliente, LocalDate data, LocalTime horaEntrada) {
        this.id = nextId++;
        this.horaEntrada = LocalDateTime.of(data, horaEntrada);
        this.cliente = cliente;
        this.foiAtendida = false;
        this.data = data;
        this.quantidade = quantidade;
    }

    /**
     * Obtém o ID da requisição.
     *
     * @return O ID da requisição.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtém a quantidade de pessoas na requisição.
     *
     * @return A quantidade de pessoas na requisição.
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Obtém o cliente associado à requisição.
     *
     * @return O cliente associado à requisição.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Define a mesa associada à requisição.
     *
     * @param mesa A mesa associada à requisição.
     */
    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    /**
     * Obtém a mesa associada à requisição.
     *
     * @return A mesa associada à requisição.
     */
    public Mesa getMesa() {
        return mesa;
    }

    /**
     * Define o pedido associado à requisição.
     *
     * @param pedido O pedido associado à requisição.
     */
    public void setPedido(IPedido pedido) {
        this.pedido = pedido;
    }

    /**
     * Obtém o pedido associado à requisição.
     *
     * @return O pedido associado à requisição.
     */
    public IPedido getPedido() {
        return pedido;
    }

    /**
     * Fecha a requisição, registrando a hora de saída e desocupando a mesa, se aplicável.
     */
    public void fecharRequisicao() {
        this.horaSaida = LocalDateTime.now();
        if (mesa != null) {
            mesa.desocupar();
        }
    }

    /**
     * Verifica se a requisição foi atendida.
     *
     * @return true se a requisição foi atendida, false caso contrário.
     */
    public boolean foiAtendida() {
        return foiAtendida;
    }

    /**
     * Define se a requisição foi atendida.
     *
     * @param foiAtendida true se a requisição foi atendida, false caso contrário.
     */
    public void setAtendida(boolean foiAtendida) {
        this.foiAtendida = foiAtendida;
    }

    /**
     * Obtém a hora de entrada da requisição.
     *
     * @return A hora de entrada da requisição.
     */
    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    /**
     * Obtém a hora de saída da requisição.
     *
     * @return A hora de saída da requisição.
     */
    public LocalDateTime getHoraSaida() {
        return horaSaida;
    }

    /**
     * Obtém uma representação textual das informações da requisição.
     *
     * @return Uma string com as informações da requisição formatadas.
     */
    public String getRequisicaoInfo() {
        String mesaId = (mesa != null) ? String.valueOf(mesa.getId()) : "N/A";
        return String.format("ID: %d, Cliente: %s, Quantidade: %d, Data: %s, Hora de Entrada: %s, Hora de Saída: %s, Mesa ID: %s",
                id, cliente.getNome(), quantidade, data, horaEntrada, horaSaida, mesaId);
    }
}
