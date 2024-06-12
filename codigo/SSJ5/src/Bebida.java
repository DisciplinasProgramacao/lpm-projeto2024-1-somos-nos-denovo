package codigo.SSJ5.src;

public enum Bebida {
    AGUA(1, 3),
    VINHO_VEGANO(2, 18),
    REFRIGERANTE_ORGANICO(3, 7),
    CERVEJA_VEGANA(4, 9),
    COPO_SUCO(5, 7);

    private final int id;
    private final double preco;

    Bebida(int id, double preco) {
        this.id = id;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public double getPreco() {
        return preco;
    }
    
    public static Bebida getById(int id) {
        for (Bebida bebida : values()) {
            if (bebida.id == id) {
                return bebida;
            }
        }
        throw new IllegalArgumentException("Opção de bebida inválida.");
    }
}
