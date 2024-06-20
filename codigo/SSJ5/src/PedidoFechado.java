package codigo.SSJ5.src;

import java.util.List;

/**
 * Classe que representa um Pedido Fechado, herda da classe abstrata Pedido.
 */
public class PedidoFechado implements IPedido{

    private List<Produto> produtos;
    private final static double taxa = 1.1;
    private static final double MENU_FIXO_PRECO = 32;

    /**
     * Construtor que inicializa um pedido fechado.
     */
    public PedidoFechado( List<Produto> produtos) {
        this.produtos = produtos;
    }


    /**
     * Calcula o valor final do pedido fechado, considerando o preço fixo do menu, o número de produtos e a taxa.
     *
     * @return Valor final do pedido fechado, após aplicação da taxa.
     */
    @Override
    public double calcularValorFinal() {
        return MENU_FIXO_PRECO *  taxa;
    }

    /**
     * Adiciona um produto ao pedido fechado, verificando se o produto é válido para o menu fechado.
     *
     * @param produto Produto a ser adicionado ao pedido.
     * @throws IllegalArgumentException Se o produto não é válido para o menu fechado.
     */
    @Override
    public void addProduto(Produto produto) {
        if !MenuFechado.contem(Produto)
            excecao
    }

    /**
     * Formata o pedido fechado em uma string detalhada.
     *
     * @return String com os itens do pedido fechado, o preço fixo do menu e o preço total formatado.
     */
    @Override
    public String formatPedido() {
        StringBuilder sb = new StringBuilder();
        sb.append("Itens do Pedido (Menu Fechado):\n");
        sb.append(String.format(" - Comida e Bebidas (Preço Fixo): R$%.2f\n", MENU_FIXO_PRECO));
        sb.append(String.format("Preço Total: R$%.2f\n", calcularValorFinal()));
        return sb.toString();
    }
}
