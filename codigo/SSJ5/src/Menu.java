package codigo.SSJ5.src;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<Produto> menu;

    public Menu() {
        this.menu = new ArrayList<>();
        Produto primeiro = new Produto("vazio", 0);
        menu.add(primeiro);

        for (EProdutoMenuAberto menuGeral : EProdutoMenuAberto.values()) {
            Produto novoProduto = new Produto(menuGeral.name(), menuGeral.getPreco());
            menu.add(novoProduto);
        }
    }

    public String exibirMenu() {
        StringBuilder sb = new StringBuilder();
        for (Produto p : menu) {
            sb.append(p.getIdProduto()).append(" ").append(p.getNomeProduto()).append(" - R$").append(p.getPrecoProduto()).append("\n");
        }
        return sb.toString();
    }

    public String exibirMenuFechado() {
        StringBuilder sb = new StringBuilder();
        for (EProdutoMenuFechado produtoFechado : EProdutoMenuFechado.values()) {
            sb.append(produtoFechado.getId()).append(" ").append(produtoFechado.name()).append(" - R$").append(produtoFechado.getPreco()).append("\n");
        }
        return sb.toString();
    }

    public Produto getProdutoById(int id) {
        for (Produto p : menu) {
            if (p.getIdProduto() == id) {
                return p;
            }
        }
        return null;
    }

    public Produto getProdutoByName(String nome) {
        for (Produto p : menu) {
            if (p.getNomeProduto().equalsIgnoreCase(nome)) {
                return p;
            }
        }
        return null;
    }
}
