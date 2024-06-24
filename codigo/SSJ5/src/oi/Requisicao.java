package codigo.SSJ5.src.oi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

    public Requisicao(int quantidade, Cliente cliente, LocalDate data, LocalTime horaEntrada) {
       this.id = nextId++;
       this.horaEntrada = LocalDateTime.now();
       this.cliente = cliente;
       this.foiAtendida = false;
       this.data = data;
       this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setPedido(IPedido pedido) {
        this.pedido = pedido;
    }

    public IPedido getPedido() {
        return pedido;
    }

    public void fecharRequisicao() {
        this.horaSaida = LocalDateTime.now();
        if (mesa != null) {
            mesa.desocupar();
        }
    }

    public boolean foiAtendida() {
        return foiAtendida;
    }

    public void setAtendida(boolean foiAtendida) {
        this.foiAtendida = foiAtendida;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public LocalDateTime getHoraSaida() {
        return horaSaida;
    }

    public String getRequisicaoInfo() {
        String mesaId = (mesa != null) ? String.valueOf(mesa.getId()) : "N/A";
        return String.format("ID: %d, Cliente: %s, Quantidade: %d, Data: %s, Hora de Entrada: %s, Hora de Saída: %s, Mesa ID: %s",
                id, cliente.getNome(), quantidade, data, horaEntrada, horaSaida, mesaId);
    }
}
