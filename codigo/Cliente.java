package codigo;

public class Cliente {

    private String nome;

    public Cliente(String nome) {
        this.nome = tratarNome(nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = tratarNome(nome);
    }

    public Requisicao gerarRequisicao(int quantidade) {
        Requisicao req = new Requisicao(this.quantidade);
        return req;
    }
}