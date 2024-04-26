import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Cliente {
    private static final AtomicInteger idCounter = new AtomicInteger();
    private String nome;
    private int id;

    public Cliente(String nome) {
        this.nome = tratarNome(nome);
        this.id = idCounter.incrementAndGet();
    }

    private String tratarNome(String nome) {
        String[] partes = nome.toLowerCase().split("\\s+");
        StringBuilder nomeTratado = new StringBuilder();
        for (String parte : partes) {
            nomeTratado.append(Character.toUpperCase(parte.charAt(0)))
                       .append(parte.substring(1)).append(" ");
        }
        return nomeTratado.toString().trim();
    }

    public Requisicao gerarRequisicao(int quantidade) {
        return new Requisicao(quantidade, this, LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(2));
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", id=" + id +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }
}
