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
    private List<IPedido> pedidos;
    private IPedido pedidoAtual;

    /**
     * Construtor da classe Requisicao.
     * 
     * @param quantidade  Quantidade de pessoas na requisição.
     * @param cliente     Cliente associado à requisição.
     * @param data        Data da requisição.
     * @param horaEntrada Hora de entrada da requisição.
     */
    public Requisicao(int quantidade, Cliente cliente) {
        this.quantidade = quantidade;
        this.cliente = cliente;
        this.data = LocalDate.now();
        this.horaEntrada = LocalTime.now();
        this.id = nextId++;
        this.pedidos = new ArrayList<>();
    }

    /**
     * Fecha a requisição, registrando o horário de saída e desocupando a mesa
     * associada, se houver.
     * 
     * @return Horário de saída da requisição.
     */
    public LocalTime fecharRequisicao() {
        this.horaSaida = LocalTime.now();
        if (mesa != null) {
            mesa.desocupar();
        }
        return horaSaida;
    }

    /**
     * Adiciona um pedido à requisição.
     * 
     * @param pedido Pedido a ser adicionado.
     */
    public void addPedido(PedidoAberto pedido) {
        this.pedidoAtual = pedido;
        this.pedidos.add(pedido);
    }

    /**
     * Calcula o valor final da requisição somando o valor de todos os pedidos.
     * 
     * @return Valor final da requisição.
     */
    public double calcularValorFinal() {
        double valorFinal = 0;
        for (IPedido pedido : pedidos) {
            valorFinal += pedido.calcularValorFinal();
        }
        return valorFinal;
    }

    /**
     * Calcula o valor por pessoa na requisição.
     * 
     * @return Valor dividido por pessoa na requisição.s
     */
    public double calcularValorPorPessoa() {
        return calcularValorFinal() / quantidade;
    }

    /**
     * Retorna uma representação em texto com iformaçoes da requisição.
     * 
     * @return Informações da requisição.
     */
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
            for (IPedido pedido : pedidos) {
                sb.append(pedido.formatPedido()).append("\n");
            }
        }

        return sb.toString();
    }

    // #region Getters & Setters

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

    public boolean addProduto(Produto p) {
        pedidoAtual.addProduto(p);
        return true;
    }

    // public PedidoAberto getPedidoAtual() {
    //     return pedidoAtual;
    // }

    // public List<PedidoAberto> getPedidos() {
    //     return pedidos;
    // }

    // #endregion

}
