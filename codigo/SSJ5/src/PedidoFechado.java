package codigo.SSJ5.src;

public class PedidoFechado extends Pedido {
    private static final double TAXA = 1.1;

    public PedidoFechado(Requisicao requisicao) {
        super(requisicao);
    }

    @Override
    public double calcularValorFinal() {
        return MENU_FIXO_PRECO * TAXA;
    }
}