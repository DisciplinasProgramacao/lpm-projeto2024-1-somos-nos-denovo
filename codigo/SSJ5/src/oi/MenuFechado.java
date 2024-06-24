package codigo.SSJ5.src.oi;

/**
 * Classe MenuFechado representa um menu fechado de um restaurante,
 * contendo uma lista predefinida de produtos disponíveis.
 */
public class MenuFechado extends Menu {

    /**
     * Construtor da classe MenuFechado.
     * Inicializa o menu com uma lista predefinida de produtos.
     */
    public MenuFechado() {
        menu.add(new Produto("FALAFEL_ASSADO", 20));
        menu.add(new Produto("CACAROLA_LEGUMES", 22));
        menu.add(new Produto("SALADA_PRIMAVERA", 25));
    }
}
