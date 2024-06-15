package codigo.SSJ5.src;

public class PedidoFechado extends Pedido {
    private static final double MENU_FIXO_PRECO = 32;

    public PedidoFechado() {
        super();
    }

    @Override
    public double calcularValorFinal() {
        return MENU_FIXO_PRECO * produtos.size() / 3 * taxa;
    }

    @Override
    public void addProduto(Produto produto) {
        if (EProdutoMenuFechado.isProdutoValido(produto.getIdProduto())) {
            produtos.add(produto);
        } else {
            throw new IllegalArgumentException("Opção não está disponível no menu fechado.");
        }
    }

    @Override
    public String formatPedido() {
        StringBuilder sb = new StringBuilder();
        sb.append("Itens do Pedido (Menu Fechado):\n");
        sb.append(String.format(" - Comida e Bebidas (Preço Fixo): R$%.2f\n", MENU_FIXO_PRECO));
        sb.append(String.format("Preço Total: R$%.2f\n", calcularValorFinal()));
        return sb.toString();
    }
}
