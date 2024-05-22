package codigo.SSJ5.src;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

public class Requisicao {

    private int quantidadePessoas;
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
    public Requisicao(int quantidadePessoas, Cliente cliente, LocalDate data, LocalTime horaEntrada,
            LocalTime horaSaida, Restaurante restaurante) {
        this.quantidadePessoas = quantidadePessoas;
        this.cliente = cliente;
        this.data = data;
        this.horaEntrada = horaEntrada;
        this.horaSaida = horaSaida;
        this.id = nextId++;
        this.status = true;
        this.restaurante = restaurante;
        this.pedido = new Pedido();
    }

    /**
     * Fecha uma requisição, desocupa a mesa e adiciona a requisição ao histórico.
     * 
     * @param requisicao          A requisição que vai ser fechada.
     * @param historicoRequisicao A lista de historico das requisicoes.
     * @return A hora de saída.
     */
    public LocalTime fecharRequisicao(Requisicao requisicao, List<Requisicao> historicoRequisicao) {
        this.horaSaida = LocalTime.now();
        if (mesa != null) {
            restaurante.fecharConta(requisicao);
            restaurante.desocuparMesa(requisicao, mesa);
        } else {
            System.out.println("Erro: A mesa associada à requisição não foi definida.");
        }
        return horaSaida;
    }

    public int getQuantidadePessoas() {
        return quantidadePessoas;
    }

    public void setquantidadePessoas(int quantidadePessoas) {
        this.quantidadePessoas = quantidadePessoas;
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

    public int getQuantidade() {
        return this.quantidadePessoas;
    }

    public int setQuantidade(int quantidade) {
        return this.quantidadePessoas = quantidade;
    }

    /**
     * Exibe o histórico de requisições.
     */
    public void exibirHistoricoDeRequisicoes() {
        List<Requisicao> historicoDeRequisicao = restaurante.getHistoricoDeRequisicao();
        if (historicoDeRequisicao.isEmpty()) {
            System.out.println("Não há requisições no histórico.");
        } else {
            System.out.println("Histórico de Requisições:");
            for (Requisicao requisicao : historicoDeRequisicao) {
                System.out.println("ID: " + requisicao.getId() + ", Cliente: " + requisicao.getCliente().getNome()
                        + ", Quantidade: " + requisicao.getQuantidadePessoas() + ", Data: "
                        + requisicao.getData() + ", Hora de Entrada: " + requisicao.getHoraEntrada()
                        + ", Hora de Saída: " + requisicao.getHoraSaida());
            }
        }
    }

}
