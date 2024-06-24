package codigo.SSJ5.src.oi;

/**
 * Produto representa um produto disponível em um restaurante.
 * Cada produto possui um ID único, um nome e um preço.
 */
public class Produto {
    private static int nextId = 0;
    private int idProduto;
    private String nomeProduto;
    private double precoProduto;

    /**
     * Construtor da classe Produto.
     * Inicializa o produto com o nome e o preço especificados, e atribui um ID único ao produto.
     *
     * @param nomeProduto  O nome do produto.
     * @param precoProduto O preço do produto.
     */
    public Produto(String nomeProduto, double precoProduto) {
        this.idProduto = nextId++;
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
    }

    /**
     * Obtém o ID do produto.
     *
     * @return O ID do produto.
     */
    public int getIdProduto() {
        return idProduto;
    }

    /**
     * Obtém o nome do produto.
     *
     * @return O nome do produto.
     */
    public String getNomeProduto() {
        return nomeProduto;
    }

    /**
     * Obtém o preço do produto.
     *
     * @return O preço do produto.
     */
    public double getPrecoProduto() {
        return precoProduto;
    }

    /**
     * Verifica se dois produtos são iguais com base no nome (ignorando maiúsculas e minúsculas).
     *
     * @param obj O objeto a ser comparado com o produto atual.
     * @return true se os produtos tiverem o mesmo nome (ignorando maiúsculas e minúsculas), false caso contrário.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Produto produto = (Produto) obj;
        return nomeProduto.equalsIgnoreCase(produto.nomeProduto);
    }

    /**
     * Calcula o hash code do nome do produto em minúsculas.
     *
     * @return O hash code do nome do produto em minúsculas.
     */
    @Override
    public int hashCode() {
        return nomeProduto.toLowerCase().hashCode();
    }
}
