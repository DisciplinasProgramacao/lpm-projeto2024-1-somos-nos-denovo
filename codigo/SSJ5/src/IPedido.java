package codigo.SSJ5.src;

import java.util.ArrayList;
import java.util.List;


//Interface? Todos os métodos estão abstratos.

/**
 * Classe abstrata que representa um Pedido.
 */
public interface IPedido {

    /**
     * Formata o pedido em uma string detalhada.
     *
     * @return String com os itens do pedido e o preço total formatado.
     */
    public String formatPedido();

    /**
     * Método abstrato para adicionar um produto ao pedido.
     *
     * @param produto Produto a ser adicionado ao pedido.
     */
    public void addProduto(Produto produto);

    /**
     * Calcula o valor final do pedido aberto, considerando a taxa.
     *
     * @return Valor final do pedido aberto, após aplicação da taxa.
     */
    public double calcularValorFinal();
}
