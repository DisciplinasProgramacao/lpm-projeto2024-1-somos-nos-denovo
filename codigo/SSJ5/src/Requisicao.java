import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class Requisicao {
    private int quantidade;
    private Cliente cliente;
    private LocalDate data;
    private LocalTime horaEntrada;
    private LocalTime horaSaida;
    private int id;

    /**
     * Construtor da classe Requisicao.
     * 
     * @param quantidade  Quantidade de pessoas na requisição
     * @param cliente     Cliente associado à requisição
     * @param data        Data da requisição
     * @param horaEntrada Hora de entrada da requisição
     * @param horaSaida   Hora de saída da requisição
     * @param id          ID do cliente associado à requisição
     */

    public Requisicao(int quantidade, Cliente cliente, LocalDate data, LocalTime horaEntrada, LocalTime horaSaida,
            int id) {
        this.quantidade = quantidade;
        this.cliente = cliente;
        this.data = data;
        this.horaEntrada = horaEntrada;
        this.horaSaida = horaSaida;
        this.id = id;
    }

    // Getters e Setters
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

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Método para fechar a conta e calcular a hora de saída.
     * 
     * @param mesas Um array de objetos Mesa que representam as mesas do restaurante
     * @return A hora de saída após fechar a conta ou null se a requisição não
     *         estiver associada a nenhuma mesa
     */
    public LocalTime fecharConta(Mesa[] mesas) {
        // Verifica se a mesa em que a requisição está foi encontrada
        boolean mesaEncontrada = false;
        for (Mesa mesa : mesas) {
            if (mesa.getRequisicao().contains()) {
                mesaEncontrada = true;

                Duration duracaoRefeicao = Duration.ofHours(1);

                LocalTime horaSaida = horaEntrada.plus(duracaoRefeicao);

                this.setHoraSaida(horaSaida);
                Restaurante.removerRequisicao(this);

                return horaSaida;
            }
        }
        /**
         * Retorna null se a requisição não estiver associada a nenhuma mesa
         */
        if (!mesaEncontrada) {
            return null;
        }
        return null;
    }
}
