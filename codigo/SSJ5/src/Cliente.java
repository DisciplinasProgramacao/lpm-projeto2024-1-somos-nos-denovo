package codigo.SSJ5.src;

public class Cliente {
    private String nome;
    private static int nextId = 0;
    private int id;

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

    @Override
    public boolean equals(Object cli) {
        if (this == cli) return true;
        if (cli == null || getClass() != cli.getClass()) return false;
        Cliente cliente = (Cliente) cli;
        return nome.equals(cliente.nome);
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }
}
