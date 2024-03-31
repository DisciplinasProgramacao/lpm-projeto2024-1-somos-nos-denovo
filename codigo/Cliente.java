package codigo;

public class Cliente {

    private String nome;

    public Cliente(String nome) {
        this.nome = nome.toUpperCase();
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
