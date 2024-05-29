package codigo.SSJ5.src;

public class Produto {
    private static int nextId = 0;
    private int idProduto;
    private String nomeProduto;
    private double precoProduto;

    public Produto(String nomeProduto, double precoProduto) {
        this.idProduto = nextId++;
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public double getPrecoProduto() {
        return precoProduto;
    }
}
