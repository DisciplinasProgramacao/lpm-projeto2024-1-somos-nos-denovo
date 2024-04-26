import java.util.concurrent.atomic.AtomicInteger;

public class Mesa {
    private static final AtomicInteger count = new AtomicInteger();
    private int capacidade;
    private boolean disponibilidade;
    private int id;
    private Requisicao requisicao;  // Atributo para armazenar a requisição associada à mesa

  

    public Mesa(int capacidade, boolean disponibilidade) {
        this.capacidade = capacidade;
        this.disponibilidade = disponibilidade;
        this.id = count.incrementAndGet();
        this.requisicao = null;  // Inicialmente, não há nenhuma requisição associada à mesa
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public int getId() {
        return id;
    }

    public Requisicao getRequisicao() {
        return requisicao;
    }

    public void setRequisicao(Requisicao requisicao) {
        this.requisicao = requisicao;
    }
}
