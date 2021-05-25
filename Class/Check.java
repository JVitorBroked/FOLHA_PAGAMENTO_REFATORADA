package Class;
import Interfaces.PaymentMethod;

public class Check implements PaymentMethod {

    private String name;

    public Check(String name){
        this.name = name;
    }

    @Override
    public void do_payment(double value) {
        System.out.println("Pagamento via cheque para " + this.name + ", no valor de " + value + " realizado com sucesso");
        
    }

    
}
