package com.ssj5.lpm.models;

import java.util.List;

/**
 * Classe abstrata que representa um menu de produtos em um restaurante.
 */
public abstract class Menu {
    protected List<Produto> menu;

    /**
     * Retorna a lista de produtos do menu.
     *
     * @return Lista de produtos do menu.
     */
    public List<Produto> getMenu() {
        return menu;
    }

    /**
     * Obtém um produto pelo seu ID no menu.
     *
     * @param idProduto ID do produto a ser buscado.
     * @return Produto correspondente ao ID, ou null se não encontrado.
     */
    public Produto getProdutoById(Long idProduto) {
        return menu.stream()
                .filter(p -> p.getId().equals(idProduto))
                .findFirst()
                .orElse(null);
    }

    /**
     * Exibe o menu completo com ID, nome e preço dos produtos.
     *
     * @return String contendo a representação do menu.
     */
    protected String exibirMenu() {
        StringBuilder sb = new StringBuilder();
        for (Produto p : menu) {
            sb.append(p.getId()).append(" - ").append(p.getNome()).append(": R$").append(p.getPreco()).append("\n");
        }
        return sb.toString();
    }
}
