package codigo.SSJ5.src;
import java.util.List;
import java.util.ArrayList;
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
    private boolean status;
    private Restaurante restaurante;

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
    }

    /**
     * Fecha uma requisição, desocupa a mesa e adiciona a requisição ao histórico.
     * @param requisicao A requisição que vai ser fechada.
     * @param historicoRequisicao A lista de historico das requisicoes.
     * @return A hora de saída.
     */
    public LocalTime fecharRequisicao(Requisicao requisicao, List<Requisicao> historicoRequisicao){
        this.horaSaida = LocalTime.now();
        restaurante.fecharConta(requisicao);
        restaurante.desocuparMesa(requisicao, mesa);


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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


}
