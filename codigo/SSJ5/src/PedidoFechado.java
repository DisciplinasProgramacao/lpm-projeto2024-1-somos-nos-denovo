package codigo.SSJ5.src;

public class PedidoFechado extends Pedido {
    private static final double MENU_FIXO_PRECO = 32;
    private static final double TAXA = 1.1;

    public PedidoFechado(Requisicao requisicao) {
        super(requisicao);
    }

    @Override
    public double calcularValorFinal() {
        return MENU_FIXO_PRECO * TAXA;
    }

    @Override
    public void fecharConta() {
        valorTotal = calcularValorFinal();
    }

    @Override
    public void addProduto(Produto produto) {
        String nome = produto.getNomeProduto();
        if (nome.equals("FALAFEL_ASSADO") || nome.equals("CACAROLA_LEGUMES") ||
            nome.equals("COPO_SUCO") || nome.equals("REFRIGERANTE_ORGANICO") || nome.equals("CERVEJA_VEGANA")) {
            super.addProduto(produto);
        } else {
            throw new IllegalArgumentException("Opção não está disponível no menu fechado.");
        }
    }
}
