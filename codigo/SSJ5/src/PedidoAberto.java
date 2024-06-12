package codigo.SSJ5.src;

public class PedidoAberto extends Pedido {

    public PedidoAberto(Requisicao requisicao) {
        super(requisicao);
    }

    @Override
    public double calcularValorFinal() {
        return calcularValorTotal() * taxa;
    }

    @Override
    public void fecharConta() {
        valorTotal = calcularValorFinal();
    }
}
