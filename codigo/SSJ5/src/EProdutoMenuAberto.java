package codigo.SSJ5.src;

public enum EProdutoMenuAberto {
    FALAFEL_ASSADO(1, 20),
    CACAROLA_LEGUMES(2, 22),
    SALADA_PRIMAVERA(3, 25),
    ESCONDIDINHO_INHAME(4, 18),
    STROGONOFF_COGUMELOS(5, 35),
    MOQUECA_PALMITO(6, 32),
    AGUA(7, 3),
    VINHO_VEGANO(8, 18),
    REFRIGERANTE_ORGANICO(9, 7),
    CERVEJA_VEGANA(10, 9),
    COPO_SUCO(11, 7);

    private final int id;
    private final double preco;

    EProdutoMenuAberto(int id, double preco) {
        this.id = id;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public double getPreco() {
        return preco;
    }

    public static EProdutoMenuAberto getById(int id) {
        for (EProdutoMenuAberto produto : values()) {
            if (produto.id == id) {
                return produto;
            }
        }
        throw new IllegalArgumentException("Opção de produto inválida.");
    }
}



