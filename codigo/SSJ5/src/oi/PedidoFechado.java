package codigo.SSJ5.src.oi;

import java.util.ArrayList;
import java.util.List;

public class PedidoFechado implements IPedido {
    private double valorTotal = 0;
    private List<Produto> produtos;
    private final static double taxa = 1.1;

    public PedidoFechado() {
        this.produtos = new ArrayList<>();
    }

    private double calcularValorTotal() {
        valorTotal = 0d;
        for (Produto p : produtos) {
            valorTotal += p.getPrecoProduto();
        }
        return valorTotal;
    }

    @Override
    public double calcularValorFinal() {
        return calcularValorTotal() + 32 * taxa;
    }

    @Override
    public double dividirConta() {
        return calcularValorFinal() / produtos.size();
    }

    @Override
    public void addProduto(Produto produto) {
        this.produtos.add(produto);
    }

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
