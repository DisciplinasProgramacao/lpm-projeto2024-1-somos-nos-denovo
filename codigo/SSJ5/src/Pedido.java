package codigo.SSJ5.src;

import java.util.ArrayList;
import java.util.List;

public abstract class Pedido {
    protected double valorTotal = 0;
    protected List<Produto> produtos;
    protected final static double taxa = 1.1;
    protected Requisicao requisicao;

    public Pedido(Requisicao requisicao) {
        this.produtos = new ArrayList<>();
        this.requisicao = requisicao;
    }

    protected double calcularValorTotal() {
        valorTotal = 0d;
        for (Produto p : produtos) {
            valorTotal += p.getPrecoProduto();
        }
        return valorTotal;
    }

    public abstract double calcularValorFinal();

    public double dividirConta() {
        return calcularValorFinal() / requisicao.getQuantidade();
    }

    public void addProduto(Produto produto) {
        this.produtos.add(produto);
    }

    public String formatPedido() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("ID Requisição: %d\n", requisicao.getId()));
        Mesa mesa = requisicao.getMesa();
        sb.append(String.format("ID Mesa: %d\n", mesa.getId()));
        sb.append(String.format("Nome do Dono da Requisição: %s\n", requisicao.getCliente().getNome()));
        sb.append("Itens do Pedido:\n");
        for (Produto p : produtos) {
            sb.append(String.format(" - %s: R$%.2f\n", p.getNomeProduto(), p.getPrecoProduto()));
        }
        sb.append(String.format("Preço Total: R$%.2f\n", calcularValorFinal()));
        sb.append(String.format("Preço por pessoa: R$%.2f\n", dividirConta()));
        return sb.toString();
    }

    public abstract void fecharConta();
}
