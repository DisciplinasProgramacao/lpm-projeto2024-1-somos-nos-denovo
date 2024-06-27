package com.ssj5.lpm.models;

import java.util.List;

/**
 * Classe abstrata para representar o menu.
 */
public abstract class Menu {
    protected List<Produto> menu;

    public List<Produto> getMenu() {
        return menu;
    }

    public Produto getProdutoById(Long idProduto) {
        return menu.stream()
                .filter(p -> p.getId().equals(idProduto))
                .findFirst()
                .orElse(null);
    }

    public String exibirMenu() {
        StringBuilder sb = new StringBuilder();
        for (Produto p : menu) {
            sb.append(p.getId()).append(" - ").append(p.getNome()).append(": R$").append(p.getPreco()).append("\n");
        }
        return sb.toString();
    }
}
