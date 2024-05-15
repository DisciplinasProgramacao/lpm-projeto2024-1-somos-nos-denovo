package codigo.SSJ5.src;

public class Mesa {
    private int capacidade;
    private boolean disponibilidade;
    private static int nextId = 0;
    private int id;
    private Requisicao requisicao;

    /**
     * Construtor da classe mesa.
     * @param capacidade Capacidade da mesa.
     * @param disponibilidade Disponibilidade da mesa (Livre ou ocupada).
     */
    public Mesa(int capacidade, boolean disponibilidade) {
        this.capacidade = capacidade;
        this.disponibilidade = disponibilidade;
        this.id = nextId++;
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

    public Requisicao getRequisicao() { // Getter para o campo Requisicao
        return requisicao;
    }

    public void setRequisicao(Requisicao requisicao) { // Setter para o campo Requisicao
        this.requisicao = requisicao;
    }

}
