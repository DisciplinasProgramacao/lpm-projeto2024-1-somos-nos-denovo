package codigo.SSJ5.src;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private List<Produto> menu;

    /**
     * Construtor
     */
    public Menu() {
        this.menu = new ArrayList<Produto>();
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

//    public int retornaIdProduto(String nomeProduto){
//        for(Produto p : menu){
//            if(nomeProduto.equals(p.getNomeProduto()))
//            return p.getIdProduto();
//        }
//        return 0;
//    }

    public void exibirMenu(){
        for(Produto p : menu){
           // System.out.println(p.getIdProduto()+" "+p.getNomeProduto()+"- R$"+ p.getPrecoProduto()); mudar para string
        }
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
