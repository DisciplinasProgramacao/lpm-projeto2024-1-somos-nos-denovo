package codigo.SSJ5.src;

import java.time.LocalDate;
import java.time.LocalTime;

public class Requisicao {
    private int quantidade;
    private Cliente cliente;
    private LocalDate data;
    private LocalTime horaEntrada;
    private LocalTime horaSaida;
    private static int nextId = 0;
    private int id;
    private Mesa mesa;
    private Pedido pedido;

    public Requisicao(int quantidade, Cliente cliente, LocalDate data, LocalTime horaEntrada) {
        this.quantidade = quantidade;
        this.cliente = cliente;
        this.data = data;
        this.horaEntrada = horaEntrada;
        this.id = nextId++;
        this.pedido = new Pedido(this);
    }

    public LocalTime fecharRequisicao() {
        this.horaSaida = LocalTime.now();
        if (mesa != null) {
            pedido.fecharConta();
            mesa.desocupar();
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

    public String getRequisicaoInfo() {
        String mesaId = (mesa != null) ? String.valueOf(mesa.getId()) : "N/A";
        return String.format("ID: %d, Cliente: %s, Quantidade: %d, Data: %s, Hora de Entrada: %s, Hora de Saída: %s, Mesa ID: %s",
                id, cliente.getNome(), quantidade, data, horaEntrada, horaSaida, mesaId);
    }
}
