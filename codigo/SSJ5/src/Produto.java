package codigo.SSJ5.src;

public class Produto {
    private int idProduto;
    private static int nextId = 0;
    private String nomeProduto;
    private double precoProduto;

    public Produto(String nomeProduto, double precoProduto) {
        this.idProduto = nextId++;
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public void setPrecoProduto(double precoProduto) {
        this.precoProduto = precoProduto;
    }

    public double getPrecoProduto() {
        return precoProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public int getIdProduto() {
        return idProduto;
    }  
}
