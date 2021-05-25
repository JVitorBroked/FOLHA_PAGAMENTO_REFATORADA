package Class;
//import java.util.Date;

public class Sale {
        
    private String produto;
    private int quantidade;
    private double preco_unitario;
    private String data;

    public Sale(String produto, int quantidade, double preco_unitario, String data){
        this.produto = produto;
        this.quantidade = quantidade;
        this.preco_unitario = preco_unitario;
        this.data = data;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public void setPreco_unitario(double preco_unitario) {
        this.preco_unitario = preco_unitario;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getProduto() {
        return produto;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public double getPreco_unitario() {
        return preco_unitario;
    }
    public String getData() {
        return data;
    }    
}
