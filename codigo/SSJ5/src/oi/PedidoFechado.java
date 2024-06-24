package codigo.SSJ5.src.oi;

import java.util.ArrayList;
import java.util.List;

/**
 * PedidoFechado representa um pedido fechado em um restaurante.
 * Implementa a interface IPedido e contém métodos para adicionar produtos,
 * calcular o valor total, calcular o valor final com taxa, dividir a conta e formatar o pedido.
 */
public class PedidoFechado implements IPedido {
    private double valorTotal = 0;
    private List<Produto> produtos;
    private static final double taxa = 1.1;

    /**
     * Construtor da classe PedidoFechado.
     * Inicializa a lista de produtos como uma nova ArrayList.
     */
    public PedidoFechado() {
        this.produtos = new ArrayList<>();
    }

    /**
     * Calcula o valor total dos produtos no pedido.
     *
     * @return O valor total dos produtos.
     */
    private double calcularValorTotal() {
        valorTotal = 0d;
        for (Produto p : produtos) {
            valorTotal += p.getPrecoProduto();
        }
        return valorTotal;
    }

    /**
     * {@inheritDoc}
     * Calcula o valor final do pedido, incluindo a taxa e um valor fixo adicional.
     *
     * @return O valor final do pedido.
     */
    @Override
    public double calcularValorFinal() {
        return calcularValorTotal() + 32 * taxa;
    }

    /**
     * {@inheritDoc}
     * Divide o valor final do pedido pelo número de produtos.
     *
     * @return O valor da conta dividido pelo número de produtos.
     */
    @Override
    public double dividirConta() {
        return calcularValorFinal() / produtos.size();
    }

    /**
     * {@inheritDoc}
     * Adiciona um produto ao pedido.
     *
     * @param produto O produto a ser adicionado ao pedido.
     */
    @Override
    public void addProduto(Produto produto) {
        this.produtos.add(produto);
    }

    /**
     * {@inheritDoc}
     * Formata os detalhes do pedido em uma string.
     *
     * @return Uma string formatada com os detalhes do pedido.
     */
    @Override
    public String formatPedido() {
        StringBuilder sb = new StringBuilder();
        sb.append("Itens do Pedido:\n");
        for (Produto p : produtos) {
            sb.append(String.format(" - %s: R$%.2f\n", p.getNomeProduto(), p.getPrecoProduto()));
        }
        sb.append(String.format("Preço Total: R$%.2f\n", calcularValorFinal()));
        sb.append(String.format("Preço por pessoa: R$%.2f\n", dividirConta()));
        return sb.toString();
    }
}
