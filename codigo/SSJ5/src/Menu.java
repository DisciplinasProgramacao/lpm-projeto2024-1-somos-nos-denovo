package codigo.SSJ5.src;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<Produto> menu;

    public Menu() {
        this.menu = new ArrayList<>();
        String[] comidas = { "Moqueca de Pamito", "Falafel Assado", "Salada Primavera com Macarrão Konjac",
                "Escondidinho de Inhame", "Strogonoff de Cogumelos", "Caçarola de legumes" };
        String[] bebidas = { "Água", "Copo de Suco", "Refrigerante orgânico", "Cerveja vegana",
                "Taça de vinho vegano" };
        Integer[] precoComida = { 32, 20, 25, 18, 35, 22 };
        Integer[] precoBebida = { 3, 7, 7, 9, 18 };

        for (int i = 0; i < comidas.length; i++) {
            Produto novoProduto = new Produto(comidas[i], precoComida[i]);
            menu.add(novoProduto);
        }
        for (int i = 0; i < bebidas.length; i++) {
            Produto novoProduto = new Produto(bebidas[i], precoBebida[i]);
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

    public Produto getProdutoById(int id) {
        for (Produto p : menu) {
            if (p.getIdProduto() == id) {
                return p;
            }
        }
        return null;
    }
}