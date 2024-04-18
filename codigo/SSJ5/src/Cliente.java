
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

    private String tratarNome(String nome) {
        String[] partesNome = nome.toLowerCase().split(" ");
        StringBuilder nomeTratado = new StringBuilder();
        for (String parte : partesNome) {
            nomeTratado.append(Character.toUpperCase(parte.charAt(0)))
                       .append(parte.substring(1)).append(" ");
        }
        return nomeTratado.toString().trim();
    }

    public Requisicao gerarRequisicao(int quantidade) {
        Requisicao req = new Requisicao(quantidade, this);
        return req;
    }
}
