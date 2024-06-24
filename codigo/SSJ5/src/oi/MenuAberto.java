package codigo.SSJ5.src.oi;

/**
 * MenuAberto representa um menu aberto de um restaurante,
 * contendo uma lista predefinida de produtos disponíveis.
 */
public class MenuAberto extends Menu {

    /**
     * Construtor da classe MenuAberto.
     * Inicializa o menu com uma lista predefinida de produtos.
     */
    public MenuAberto() {
        menu.add(new Produto("FALAFEL_ASSADO", 20));
        menu.add(new Produto("CACAROLA_LEGUMES", 22));
        menu.add(new Produto("SALADA_PRIMAVERA", 25));
        menu.add(new Produto("ESCONDIDINHO_INHAME", 18));
        menu.add(new Produto("STROGONOFF_COGUMELOS", 35));
        menu.add(new Produto("MOQUECA_PALMITO", 32));
    }
}
