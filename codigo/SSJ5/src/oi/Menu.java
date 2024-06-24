package codigo.SSJ5.src.oi;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstrata Menu representa um menu genérico que pode conter uma lista de produtos.
 * Ela fornece métodos para exibir o menu, obter um produto pelo seu ID ou pelo seu nome.
 */
public abstract class Menu {
    protected List<Produto> menu;

    /**
     * Construtor protegido da classe Menu.
     * Inicializa uma nova lista de produtos.
     */
    protected Menu() {
        this.menu = new ArrayList<>();
    }

    /**
     * Exibe o menu em formato de string.
     *
     * @return Uma string contendo todos os produtos do menu.
     */
    public String exibirMenu() {
        StringBuilder sb = new StringBuilder();
        for (Produto p : menu) {
            sb.append(p).append("\n");
        }
        return sb.toString();
    }

    /**
     * Obtém um produto pelo seu ID.
     *
     * @param id O ID do produto a ser obtido.
     * @return O produto correspondente ao ID fornecido, ou null se não encontrado.
     */
    public Produto getProdutoById(int id) {
        for (Produto p : menu) {
            if (p.getIdProduto() == id) {
                return p;
            }
        }
        return null;
    }

    /**
     * Obtém um produto pelo seu nome.
     *
     * @param nome O nome do produto a ser obtido.
     * @return O produto correspondente ao nome fornecido, ou null se não encontrado.
     */
    public Produto getProdutoByName(String nome) {
        for (Produto p : menu) {
            if (p.getNomeProduto().equalsIgnoreCase(nome)) {
                return p;
            }
        }
        return null;
    }
}
