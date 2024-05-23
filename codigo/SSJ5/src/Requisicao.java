package codigo.SSJ5.src;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Requisicao {

    private int quantidade;
    private Cliente cliente;
    private LocalDate data;
    private LocalTime horaEntrada;
    private LocalTime horaSaida;
    private static int nextId = 0;
    private int id;
    private Mesa mesa;
    private boolean status;
    private Restaurante restaurante;
    private Pedido pedido;

    /**
     * Construtor da classe Requisicao.
     */
    public Requisicao(int quantidade, Cliente cliente, LocalDate data, LocalTime horaEntrada, LocalTime horaSaida, Restaurante restaurante) {
        this.quantidade = quantidade;
        this.cliente = cliente;
        this.data = data;
        this.horaEntrada = horaEntrada;
        this.horaSaida = horaSaida;
        this.id = nextId++;
        this.status = true;
        this.restaurante = restaurante;
        this.pedido = new Pedido(this); // Ensure that the Pedido is associated with this Requisicao
    }

    public LocalTime fecharRequisicao(List<Requisicao> historicoRequisicao) {
        this.horaSaida = LocalTime.now();
        if (mesa != null) {
            pedido.fecharconta();
            mesa.setDisponibilidade(true);

        }
        return horaSaida;
    }

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

    //#region gets e sets
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public String getRequisicaoInfo() {
        return String.format("ID: %d, Cliente: %s, Quantidade: %d, Data: %s, Hora de Entrada: %s, Hora de Saída: %s, Mesa ID: %d",
                id, cliente.getNome(), quantidade, data, horaEntrada, horaSaida, (mesa != null ? mesa.getId() : -1));
    }
}
