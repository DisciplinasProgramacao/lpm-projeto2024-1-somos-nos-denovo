package codigo.SSJ5.src;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private double valorTotal;
    private List<Produto> produtos;
    private final static double taxa = 1.1;
    private Requisicao requisicao;

    public Pedido(Requisicao requisicao) {
        this.produtos = new ArrayList<>();
        this.requisicao = requisicao;
    }

    private double calcularValorTotal() {
        valorTotal = 0;
        for (Produto p : produtos) {
            valorTotal += p.getPrecoProduto();
        }
        return valorTotal;
    }

    public double calcularValorFinal() {
        return calcularValorTotal() * taxa;
    }

    public double dividirConta() {
        return (valorTotal / requisicao.getQuantidade()) * taxa;
    }

    public void addProduto(Produto produto) {
        this.produtos.add(produto);
    }

    public String formatPedido() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("ID Requisição: %d\n", requisicao.getId()));
        sb.append(String.format("ID Mesa: %d\n", requisicao.getMesa().getId()));
        sb.append(String.format("Nome do Dono da Requisição: %s\n", requisicao.getCliente().getNome()));
        sb.append("Itens do Pedido:\n");
        for (Produto p : produtos) {
            sb.append(String.format(" - %s: R$%.2f\n", p.getNomeProduto(), p.getPrecoProduto()));
        }
        sb.append(String.format("Preço Total: R$%.2f\n", calcularValorFinal()));
        return sb.toString();
    }

    public void fecharConta() {
        valorTotal = calcularValorFinal();
    }
}
