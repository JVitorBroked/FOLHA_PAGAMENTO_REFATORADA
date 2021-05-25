package Class;
import Interfaces.PaymentMethod;

public class BankAccount implements PaymentMethod {

    private String name;
    private String pix;

    public BankAccount(String name, String pix) {
        this.pix = pix;
        this.name = name;
    }

    @Override
    public void do_payment(double value) {
        System.out.println("Pagamento via pix para " + this.name + " | conta: " + this.pix + ", no valor de " + value + " realizado com sucesso");    
    }

}