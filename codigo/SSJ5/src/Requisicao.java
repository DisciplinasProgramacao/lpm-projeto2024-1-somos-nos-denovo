package codigo.SSJ5.src;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    private List<Pedido> pedidos;
    private Pedido pedidoAtual;

    public Requisicao(int quantidade, Cliente cliente, LocalDate data, LocalTime horaEntrada) {
        this.quantidade = quantidade;
        this.cliente = cliente;
        this.data = data;
        this.horaEntrada = horaEntrada;
        this.id = nextId++;
        this.pedidos = new ArrayList<>();
    }

    public LocalTime fecharRequisicao() {
        this.horaSaida = LocalTime.now();
        if (mesa != null) {
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

    public void addPedido(Pedido pedido) {
        this.pedidoAtual = pedido;
        this.pedidos.add(pedido);
    }

    public Pedido getPedidoAtual() {
        return pedidoAtual;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public double calcularValorFinal() {
        double valorFinal = 0;
        for (Pedido pedido : pedidos) {
            valorFinal += pedido.calcularValorFinal();
        }
        return valorFinal;
    }

    public double calcularValorPorPessoa() {
        return calcularValorFinal() / quantidade;
    }

    public String getRequisicaoInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String horaEntradaFormatada = horaEntrada.format(formatter);
        String horaSaidaFormatada = (horaSaida != null ? horaSaida.format(formatter) : "N/A");
        String mesaId = (mesa != null) ? String.valueOf(mesa.getId()) : "N/A";

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(
                "ID: %02d\nCliente: %s\nQuantidade: %02d\nData: %s\nHora de Entrada: %s\nHora de Saída: %s\nMesa ID: %s\n",
                id, cliente.getNome(), quantidade, data, horaEntradaFormatada, horaSaidaFormatada, mesaId));

        if (pedidos.isEmpty()) {
            sb.append("Não há pedidos no momento.\n");
        } else {
            sb.append("Pedidos:\n");
            for (Pedido pedido : pedidos) {
                sb.append(pedido.formatPedido()).append("\n");
            }
        }

        return sb.toString();
    }
}
