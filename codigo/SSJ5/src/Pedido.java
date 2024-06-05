package codigo.SSJ5.src;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private double valorTotal = 0.0;
    private List<Produto> produtos;
    private final static double taxa = 1.1;
    private Requisicao requisicao;
    private static final double MENU_FIXO_PRECO = 32.0;

    public Pedido(Requisicao requisicao) {
        this.produtos = new ArrayList<>();
        this.requisicao = requisicao;
    }

    public double calcularValorTotal() {
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
        if (requisicao.getQuantidade() == 0) {
            return 0.0;
        } else {
            return (valorTotal / requisicao.getQuantidade()) * taxa;
        }
    }

    public void addProduto(Produto produto) {
        this.produtos.add(produto);
    }

    public void addMenuFixoPreco(int quantidade) {
        valorTotal += quantidade * MENU_FIXO_PRECO;
    }

    public String formatPedido() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("ID Requisição: %d\n", requisicao.getId()));
        Mesa mesa = requisicao.getMesa();
        if (mesa != null) {
            sb.append(String.format("ID Mesa: %d\n", mesa.getId()));
        }
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

    public double getValorTotal() {
        return valorTotal;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public List<Cliente> getItens() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getItens'");
    }
}