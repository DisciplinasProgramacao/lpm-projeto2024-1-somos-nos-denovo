import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Requisicao {
    private static final AtomicInteger idCounter = new AtomicInteger();
    private int id;
    private int quantidade;
    private Cliente cliente;
    private LocalDate data;
    private LocalTime horaEntrada;
    private LocalTime horaSaida;

    public Requisicao(int quantidade, Cliente cliente, LocalDate data, LocalTime horaEntrada, LocalTime horaSaida) {
        this.id = idCounter.incrementAndGet();
        this.quantidade = quantidade;
        this.cliente = cliente;
        this.data = data;
        this.horaEntrada = horaEntrada;
        this.horaSaida = horaSaida;
    }

    public int getId() {
        return id;
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

    public LocalDate getData() {
        return data;
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
}
