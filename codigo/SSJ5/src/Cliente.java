import java.time.LocalDate;
import java.time.LocalTime;

public class Cliente {

    private String nome;
    private int id;

    public Cliente(String nome, int id) {
        this.nome = tratarNome(nome);
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = tratarNome(nome);
    }

    /**
     * Método privado para tratar o nome do cliente, garantindo que a primeira letra de cada palavra seja maiúscula.
     *
     * @param nome O nome a ser tratado
     * @return O nome tratado
     */
    private String tratarNome(String nome) {
        String[] partesNome = nome.toLowerCase().split(" ");
        StringBuilder nomeTratado = new StringBuilder();
        for (String parte : partesNome) {
            nomeTratado.append(Character.toUpperCase(parte.charAt(0)))
                    .append(parte.substring(1)).append(" ");
        }
        return nomeTratado.toString().trim();
    }

    /**
     * Método para gerar uma requisição para o cliente.
     *
     * @param quantidade A quantidade de pessoas na requisição
     * @return Uma nova requisição gerada para o cliente
     */
    public Requisicao gerarRequisicao(int quantidade) {
        // Utilize o construtor de Requisicao diretamente
        return new Requisicao(5, cliente2, LocalDate.of(2024, 4, 11), LocalTime.of(20, 0), LocalTime.of(22, 0));
    }
}
