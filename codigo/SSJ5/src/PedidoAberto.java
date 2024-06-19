package codigo.SSJ5.src;

/**
 * Classe que representa um Pedido Aberto, herda da classe abstrata Pedido.
 */
public class PedidoAberto extends Pedido {

    /**
     * Construtor que inicializa um pedido aberto.
     */
    public PedidoAberto() {
        super();
    }

    /**
     * Adiciona um produto ao pedido aberto.
     *
     * @param produto Produto a ser adicionado ao pedido.
     */
    @Override
    public void addProduto(Produto produto) {
        produtos.add(produto);
    }

    /**
     * Calcula o valor final do pedido aberto, considerando a taxa.
     *
     * @return Valor final do pedido aberto, após aplicação da taxa.
     */
    @Override
    public double calcularValorFinal() {
        return calcularValorTotal() * taxa;
    }

    /**
     * Formata o pedido aberto em uma string detalhada.
     *
     * @return String com os itens do pedido aberto e o preço total formatado.
     */
    @Override
    public String formatPedido() {
        StringBuilder sb = new StringBuilder();
        sb.append("Itens do Pedido (Menu Aberto):\n");
        for (Produto p : produtos) {
            sb.append(String.format(" - %s: R$%.2f\n", p.getNomeProduto(), p.getPrecoProduto()));
        }
        sb.append(String.format("Preço Total: R$%.2f\n", calcularValorFinal()));
        return sb.toString();
    }
}
