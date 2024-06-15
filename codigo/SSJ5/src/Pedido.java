package codigo.SSJ5.src;

import java.util.ArrayList;
import java.util.List;

public abstract class Pedido {
    protected double valorTotal = 0;
    protected List<Produto> produtos;
    protected final static double taxa = 1.1;

    public Pedido() {
        this.produtos = new ArrayList<>();
    }

    public String formatPedido() {
        StringBuilder sb = new StringBuilder();
        sb.append("Itens do Pedido:\n");
        for (Produto p : produtos) {
            sb.append(String.format(" - %s: R$%.2f\n", p.getNomeProduto(), p.getPrecoProduto()));
        }
        sb.append(String.format("Preço Total: R$%.2f\n", calcularValorFinal()));
        return sb.toString();
    }

    protected double calcularValorTotal() {
        valorTotal = 0d;
        for (Produto p : produtos) {
            valorTotal += p.getPrecoProduto();
        }
        return valorTotal;
    }

    public abstract double calcularValorFinal();

    public abstract void addProduto(Produto produto);
}
