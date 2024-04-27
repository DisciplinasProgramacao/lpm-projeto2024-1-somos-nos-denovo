import java.util.concurrent.atomic.AtomicInteger;

public class Cliente {

    
    private static final AtomicInteger count = new AtomicInteger(0);
    private String nome;
    private int id;

    public Cliente(String nome) {
        this.nome = tratarNome(nome);
        this.id = generateId();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = tratarNome(nome);
    }

    private int generateId() {
        return count.incrementAndGet();
    }

    public int getId() {
        return id;
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

}
