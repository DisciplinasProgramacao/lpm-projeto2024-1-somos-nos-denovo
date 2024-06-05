package com.codigo.ssj5.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    protected double valorTotal;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "pedido_produto",
        joinColumns = @JoinColumn(name = "pedido_id"),
        inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    protected List<Produto> produtos = new ArrayList<>();
    
    private static final double TAXA = 1.1;
    protected static final double MENU_FIXO_PRECO = 32.0;
    
    @OneToOne
    private Requisicao requisicao;

    public Pedido() {}

    public Pedido(Requisicao requisicao) {
        this.requisicao = requisicao;
    }

    protected double calcularValorTotal() {
        valorTotal = 0;
        for (Produto p : produtos) {
            valorTotal += p.getPrecoProduto();
        }
        return valorTotal;
    }

    public double calcularValorFinal() {
        return calcularValorTotal() * TAXA;
    }

    public double dividirConta() {
        return (valorTotal / requisicao.getQuantidade()) * TAXA;
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
