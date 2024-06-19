package codigo.SSJ5.src;

import java.util.ArrayList;
import java.util.List;


//Interface?

/**
 * Classe abstrata que representa um Pedido.
 */
public abstract class Pedido {

    protected double valorTotal = 0;
    protected List<Produto> produtos;
    protected final static double taxa = 1.1;

    /**
     * Construtor que inicializa a lista de produtos do pedido.
     */
    public Pedido() {
        this.produtos = new ArrayList<>();
    }

    /**
     * Formata o pedido em uma string detalhada.
     *
     * @return String com os itens do pedido e o preço total formatado.
     */
    public abstract String formatPedido();


    /**
     * Método abstrato para calcular o valor final do pedido.
     *
     * @return Valor final do pedido, após aplicação de descontos ou taxas.
     */
    public abstract double calcularValorFinal();

    /**
     * Método abstrato para adicionar um produto ao pedido.
     *
     * @param produto Produto a ser adicionado ao pedido.
     */
    public abstract void addProduto(Produto produto);
}
