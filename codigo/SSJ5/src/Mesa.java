package codigo.SSJ5.src;

public class Mesa {
    private int capacidade;
    private boolean disponibilidade;
    private static int nextId = 0;
    private int id;

    public Mesa(int capacidade, boolean disponibilidade) {
        this.capacidade = capacidade;
        this.disponibilidade = disponibilidade;
        this.id = nextId++;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public int getId() {
        return id;
    }

    public void desocupar() {
        this.disponibilidade = true;
    }
}