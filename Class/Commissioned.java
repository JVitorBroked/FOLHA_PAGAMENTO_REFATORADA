package Class;
import java.util.ArrayList;

public class Commissioned extends Employee {

    private double salary;
    private double comission;
    private ArrayList<Sale> vendas;

    public Commissioned(String nome, String endereco, double salary, double comission){
        super(nome, endereco);
        super.addPaymentSchedule(new ScheduleBiweekly());
        this.comission = comission;
        this.salary = salary;
        this.vendas = new ArrayList<Sale>();
    }

    public void setComission(double comission) {
        this.comission = comission;
    }

    public double getComission() {
        return comission;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void add_sale(String produto, int quantidade, double preco_unitario, String data){
        
        this.vendas.add(new Sale(produto, quantidade, preco_unitario, data));
    }

    public void clear_sales(){
        this.vendas.clear();
    }
    public double total_sales(){
        
        double value = 0;
        for (Sale sale : vendas) {
            value += sale.getQuantidade();
        }
        return value;
    }
    
    public void listar_vendas(){

        for(Sale sale : vendas) {
            System.out.println(sale.getProduto());     
        }
    }

    @Override
    public double valueOfPayment() {
       
       double value = total_sales() * getComission();
       value += getSalary();
       clear_sales();
       return value;
    }
    

}