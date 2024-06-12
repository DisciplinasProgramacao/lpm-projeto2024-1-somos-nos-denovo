package codigo.SSJ5.src;

public enum Comida {
    FALAFEL_ASSADO(1, 20),
    CACAROLA_LEGUMES(2, 22),
    SALADA_PRIMAVERA(3, 25),
    ESCONDIDINHO_INHAME(4, 18),
    STROGONOFF_COGUMELOS(5, 35),
    MOQUECA_PALMITO(6, 32);

    private final int id;
    private final double preco;

    Comida(int id, double preco) {
        this.id = id;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public double getPreco() {
        return preco;
    }

    public static Comida getById(int id) {
        for (Comida comida : values()) {
            if (comida.id == id) {
                return comida;
            }
        }
        throw new IllegalArgumentException("Opção de comida inválida.");
    }
}
