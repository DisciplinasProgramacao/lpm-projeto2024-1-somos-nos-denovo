package codigo.SSJ5.src.oi;

public interface IPedido {
    double calcularValorFinal();
    String formatPedido();
    void addProduto(Produto produto);
    double dividirConta();
}
