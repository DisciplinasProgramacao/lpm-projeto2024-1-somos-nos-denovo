package codigo.SSJ5.src.oi;

/**
 * Mesa representa uma mesa em um restaurante, com capacidade e disponibilidade.
 * Cada mesa tem um ID único, capacidade e estado de disponibilidade.
 */
public class Mesa {
    private int capacidade;
    private boolean disponibilidade;
    private static int nextId = 0;
    private int id;

    /**
     * Construtor da classe Mesa.
     * Inicializa a mesa com a capacidade e disponibilidade especificadas,
     * e atribui um ID único à mesa.
     *
     * @param capacidade      A capacidade da mesa.
     * @param disponibilidade A disponibilidade da mesa.
     */
    public Mesa(int capacidade, boolean disponibilidade) {
        this.capacidade = capacidade;
        this.disponibilidade = disponibilidade;
        this.id = nextId++;
    }

    /**
     * Obtém a capacidade da mesa.
     *
     * @return A capacidade da mesa.
     */
    public int getCapacidade() {
        return capacidade;
    }

    /**
     * Define a capacidade da mesa.
     *
     * @param capacidade A nova capacidade da mesa.
     */
    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    /**
     * Verifica se a mesa está disponível.
     *
     * @return true se a mesa estiver disponível, false caso contrário.
     */
    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    /**
     * Define a disponibilidade da mesa.
     *
     * @param disponibilidade A nova disponibilidade da mesa.
     */
    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    /**
     * Obtém o ID da mesa.
     *
     * @return O ID da mesa.
     */
    public int getId() {
        return id;
    }

    /**
     * Define a mesa como desocupada, tornando-a disponível.
     */
    public void desocupar() {
        this.disponibilidade = true;
    }
}
