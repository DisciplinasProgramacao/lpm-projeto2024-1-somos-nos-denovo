package codigo.SSJ5.src;

public enum EProdutoMenuFechado {
    FALAFEL_ASSADO(1, 20),
    CACAROLA_LEGUMES(2, 22),
    COPO_SUCO(3, 7),
    REFRIGERANTE_ORGANICO(4, 7),
    CERVEJA_VEGANA(5, 9);

    private final int id;
    private final double preco;

    EProdutoMenuFechado(int id, double preco) {
        this.id = id;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public double getPreco() {
        return preco;
    }

    public static boolean isProdutoValido(int idProduto) {
        for (EProdutoMenuFechado produto : values()) {
            if (produto.getId() == idProduto) {
                return true;
            }
        }
        return false;
    }

    public static boolean isComida(int idProduto) {
        return idProduto == FALAFEL_ASSADO.id || idProduto == CACAROLA_LEGUMES.id;
    }

    public static boolean isBebida(int idProduto) {
        return idProduto == COPO_SUCO.id || idProduto == REFRIGERANTE_ORGANICO.id || idProduto == CERVEJA_VEGANA.id;
    }
}
