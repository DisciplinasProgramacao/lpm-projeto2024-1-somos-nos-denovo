import java.util.concurrent.atomic.AtomicInteger;

/**
 * Classe que representa uma Mesa com atributos de capacidade, disponibilidade, id e requisição.
 */
public class Mesa {
    private static final AtomicInteger count = new AtomicInteger(0);
    private int capacidade;
    private boolean disponibilidade;
    private int id;
    private Requisicao requisicao;

    /**
     * Construtor da classe Mesa.
     *
     * @param capacidade A capacidade da mesa.
     * @param id O identificador da mesa.
     */
    public Mesa(boolean disponibilidade) {
        this.id = generateId();
        this.disponibilidade = disponibilidade; 
    }

    /**
    * Gera um ID único para cada objeto Mesa.
    *
    * @return Um ID único.
    */
    private static int generateId() {
        return count.incrementAndGet();
    }

    /**
     * Retorna a capacidade da mesa.
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
     * Retorna a disponibilidade da mesa.
     *
     * @return A disponibilidade da mesa.
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
     * Retorna o ID da mesa.
     *
     * @return O ID da mesa.
     */
    public int getId() {
        return id;
    }

    /**
     * Retorna a requisição da mesa.
     *
     * @return A requisição da mesa.
     */
    public Requisicao getRequisicao() {
        return requisicao;
    }
    
    /**
     * Define a requisição da mesa.
     *
     * @param requisicao A nova requisição da mesa.
     */
    public void setRequisicao(Requisicao requisicao) {
        this.requisicao = requisicao;
    }
}

