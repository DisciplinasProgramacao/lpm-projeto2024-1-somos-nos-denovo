package codigo.SSJ5.src.oi;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Produto produto = (Produto) obj;
        return nomeProduto.equalsIgnoreCase(produto.nomeProduto);
    }

    @Override
    public int hashCode() {
        return nomeProduto.toLowerCase().hashCode();
    }
}
