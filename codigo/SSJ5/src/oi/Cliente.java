package codigo.SSJ5.src.oi;

/**
 * Representa um cliente com um nome e um identificador único.
 * Cada cliente tem um nome e um ID que é automaticamente gerado e incrementado.
 */
public class Cliente {
    private String nome;
    private static int nextId = 0;
    private int id;

    /**
     * Construtor da classe Cliente.
     * Inicializa um novo cliente com o nome fornecido e um ID único.
     *
     * @param nome O nome do cliente.
     */
    public Cliente(String nome) {
        this.nome = nome;
        this.id = nextId++;
    }

    /**
     * Obtém o nome do cliente.
     *
     * @return O nome do cliente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do cliente.
     *
     * @param nome O novo nome do cliente.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o ID do cliente.
     *
     * @return O ID do cliente.
     */
    public int getId() {
        return id;
    }

    /**
     * Compara este cliente com o objeto especificado para igualdade.
     * Retorna verdadeiro se o objeto especificado for igual a este cliente.
     *
     * @param cli O objeto a ser comparado com este cliente.
     * @return true se o objeto especificado for igual a este cliente; false caso contrário.
     */
    @Override
    public boolean equals(Object cli) {
        if (this == cli) return true;
        if (cli == null || getClass() != cli.getClass()) return false;
        Cliente cliente = (Cliente) cli;
        return nome.equals(cliente.nome);
    }

    /**
     * Retorna um valor de código hash para o cliente.
     *
     * @return Um valor de código hash para este cliente.
     */
    @Override
    public int hashCode() {
        return nome.hashCode();
    }
}
