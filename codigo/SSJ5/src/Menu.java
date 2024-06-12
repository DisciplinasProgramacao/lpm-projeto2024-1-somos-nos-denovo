package codigo.SSJ5.src;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<Produto> menu;

    public Menu() {
        this.menu = new ArrayList<>();
        Produto primeiro = new Produto("vazio", 0);
        menu.add(primeiro);

        for (Comida comida : Comida.values()) {
            Produto novoProduto = new Produto(comida.name(), comida.getPreco());
            menu.add(novoProduto);
        }

        for (Bebida bebida : Bebida.values()) {
            Produto novoProduto = new Produto(bebida.name(), bebida.getPreco());
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
        sb.append("Menu Fechado:\n");
sb.append("1 Falafel Assado - R$20\n");
sb.append("2 Caçarola de legumes - R$22\n");
sb.append("BEBIDAS:\n");
sb.append("3 Refrigerante orgânico - R$7\n");
sb.append("4 Cerveja vegana - R$9\n");
sb.append("5 Copo de suco - R$7\n");
return sb.toString();
}

public Produto getProdutoById(int id) {
    if (id >= 0 && id < menu.size())
        return menu.get(id);
    else
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
