package codigo.SSJ5.src;

public class Cliente {
    private String nome;
    private static int nextId = 0;
    private int id;

    /**
     * Construtor da classe Cliente.
     */
    public Cliente(String nome) {
        this.nome = nome;
        this.id = nextId++;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }
}
