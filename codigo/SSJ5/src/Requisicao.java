package codigo.SSJ5.src;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Representa uma requisição feita por um cliente em um restaurante.
 */
public class Requisicao {
    private static int nextId = 0;

    private int id;
    private int quantidade;
    private Cliente cliente;
    private LocalDate data;
    private LocalTime horaEntrada;
    private LocalTime horaSaida;
    private Mesa mesa;
    private Pedido pedido;

    /**
     * Constrói uma nova Requisicao.
     *
     * @param quantidade  a quantidade de pessoas na requisição
     * @param cliente     o cliente que fez a requisição
     * @param data        a data da requisição
     * @param horaEntrada a hora de entrada da requisição
     */
    public Requisicao(int quantidade, Cliente cliente, LocalDate data, LocalTime horaEntrada) {
        this.id = nextId++;
        this.quantidade = quantidade;
        this.cliente = cliente;
        this.data = data;
        this.horaEntrada = horaEntrada;
        this.pedido = new Pedido(this);
    }

/**
 * Fecha a requisição, registrando a hora de saída, fechando o pedido e
 * desocupando a mesa.
 *
 * @return a hora de saída registrada
 */
public LocalTime fecharRequisicao() {
    this.horaSaida = LocalTime.now();
    // Fecha o pedido e desocupa a mesa
    if (mesa != null) {
        pedido.fecharConta();
        mesa.desocupar();
    }

    return horaSaida;
}


    /**
     * Adiciona um item ao pedido da requisição.
     *
     * @param produto o produto a ser adicionado ao pedido
     */
    public void adicionarItem(Produto produto) {
        if (pedido != null) {
            pedido.addProduto(produto);
        }
    }

    /**
     * Obtém informações detalhadas da requisição.
     *
     * @return uma string contendo informações da requisição
     */
    public String getRequisicaoInfo() {
        String mesaId = (mesa != null) ? String.valueOf(mesa.getId()) : "N/A";
        return String.format(
                "ID: %d, Cliente: %s, Quantidade: %d, Data: %s, Hora de Entrada: %s, Hora de Saída: %s, Mesa ID: %s",
                id, cliente.getNome(), quantidade, data, horaEntrada, horaSaida, mesaId);
    }

    // Getters e setters
    // #region

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(LocalTime horaSaida) {
        this.horaSaida = horaSaida;
    }

    public int getId() {
        return id;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    // #endregion

}