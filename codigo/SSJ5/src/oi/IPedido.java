package codigo.SSJ5.src.oi;

/**
 * Define os métodos que uma classe deve implementar para representar um pedido.
 * Um pedido inclui a capacidade de calcular o valor final, formatar a representação do pedido,
 * adicionar produtos ao pedido e dividir o valor total da conta.
 */
public interface IPedido {
    
    /**
     * Calcula o valor final do pedido.
     *
     * @return O valor final do pedido.
     */
    double calcularValorFinal();

    /**
     * Formata a representação do pedido como uma string.
     *
     * @return A representação formatada do pedido.
     */
    String formatPedido();

    /**
     * Adiciona um produto ao pedido.
     *
     * @param produto O produto a ser adicionado ao pedido.
     */
    void addProduto(Produto produto);

    /**
     * Divide o valor total da conta do pedido.
     *
     * @return O valor dividido da conta.
     */
    double dividirConta();
}
