package codigo.SSJ5.src;

public class PedidoAberto extends Pedido {

    public PedidoAberto() {
        super();
    }

    @Override
    public void addProduto(Produto produto) {
        produtos.add(produto);
    }

    @Override
    public double calcularValorFinal() {
        return calcularValorTotal() * taxa;
    }

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
