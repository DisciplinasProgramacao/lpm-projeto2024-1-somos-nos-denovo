package codigo.SSJ5.src;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int valorProduto;
    private double valorTotal;
    private List<Produto> produtos = new ArrayList<>();
    private final static double taxa = 1.1;
    private Requisicao requisicao;

    public Pedido(Requisicao requisicao) {
        this.valorProduto = 0;
        this.valorTotal = 0;
        this.requisicao = requisicao;
    }

    private double calcularValorTotal(){
        valorTotal = 0; // Reset the total before calculating
        for(Produto p : produtos){
            valorTotal += p.getPrecoProduto();
        }
        return valorTotal;
    }

    public double calcularValorFinal(){
        return calcularValorTotal() * taxa;
    }

    public double dividirConta(){
        return (valorTotal / requisicao.getQuantidade()) * taxa;
    }

    public void addProdutos(List<Produto> produtos){
        this.produtos.addAll(produtos);
    }

    public void setProdutos(List<Produto> produtos){
        this.produtos = produtos;
    }

    public int getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(int valorProduto) {
        this.valorProduto = valorProduto;
    }

    public Requisicao getRequisicao() {
        return requisicao;
    }

    public void setRequisicao(Requisicao requisicao) {
        this.requisicao = requisicao;
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
}
