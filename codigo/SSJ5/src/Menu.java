package codigo.SSJ5.src;

import java.util.ArrayList;
import java.util.List;

public abstract class Menu {
    private List<Produto> menu;

    protected Menu() {
     

    }

    public String exibirMenu() {
        StringBuilder sb = new StringBuilder();
        for (Produto p : menu) {
            sb.append(p+"\n");
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
