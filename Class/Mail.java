package Class;
import Interfaces.PaymentMethod;

public class Mail implements PaymentMethod {

    private String name;
    private String endereco;

    public Mail(String name, String endereco){
        this.name = name;
        this.endereco = endereco;
    }

    @Override
    public void do_payment(double value) {
        System.out.println("Pagamento via cheque correios para " + this.name + ", enviado para endereco: " + this.endereco + ", no valor de " + value + " realizado com sucesso");        
    }

    
}
