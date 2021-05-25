package Class;
import Interfaces.*;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

public abstract class Employee {

    private String nome;
    private String endereco;
    private UUID id;
    private Syndicate syndicate;
    private PaymentSchedule paymentschedule;
    private PaymentMethod paymentMethod;

    //adiconar metodos abstratos para todas as herenacas(hourly, s, c)

    public Employee(String nome, String endereco){
        this.nome = nome;
        this.id = UUID.randomUUID();
        this.endereco = endereco;
        this.syndicate = null;
        //adicionar metodo pra adicinar id aleatario
    }

    public abstract double valueOfPayment();
    
    public String getNome(){
        return this.nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public UUID getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String print_employee_info(){
        return "Name: " + this.nome + 
                "\nAddress: " + this.endereco + 
                "\nID: " + this.id;
    }

    public void add_syndicate(double monthly_fee){
        this.syndicate = new Syndicate(monthly_fee);
    }   
    
    public Syndicate getSyndicate() {
        return syndicate;
    }
   public void setSyndicate(Syndicate syndicate) {
       this.syndicate = syndicate;
   }

   public double PayMonthlyFee(){
        if(this.syndicate != null){
            if(getPaymentschedule().getLast_payment() == null || 
                getPaymentschedule().getLast_payment().getMonthValue() != 
                        LocalDate.now().getMonthValue()){
                
                return getSyndicate().getMonthly_fee();
                }
        }
        return 0;
   }

    public PaymentSchedule getPaymentschedule() {
       return paymentschedule;
    }
    
    public void addPaymentSchedule(Schedule schedule){
        this.paymentschedule = new PaymentSchedule(schedule);
    }


    public void change_payment_schedule(String schedule){

        if(schedule.equals("semanal")){
            this.paymentschedule = new PaymentSchedule(new ScheduleWeekly());
        }else if(schedule.equals("bisemanal")){
            this.paymentschedule = new PaymentSchedule(new ScheduleBiweekly());
        }else if(schedule.equals("mensal")){
            this.paymentschedule = new PaymentSchedule(new ScheduleMonthly());
        }else{
            this.paymentschedule = new PaymentSchedule(new ScheduleCustom(schedule));
        }
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    
    public void setPaymentMethod(PaymentMethod pm){
        this.paymentMethod = pm;
    }

    public void setPaymentMethod(String pm) {

        Scanner input = new Scanner(System.in);
        if(pm.equals("correios")){
            this.paymentMethod = new Mail(this.nome, this.endereco);
            System.out.println("METODO DE PAGAMENTO POR CORREIOS ASSOCIADO A " + this.nome);
        }else if(pm.equals("deposito")){
            System.out.println("DIGITE O PIX DO EMPREGADO: ");
            String pix = input.nextLine();
            this.paymentMethod = new BankAccount(this.nome, pix);
            System.out.println("METODO DE PAGAMENTO POR PIX ASSOCIADO A " + this.nome);
        }else if(pm.equals("cheque")){
            this.paymentMethod = new Check(this.nome);
            System.out.println("METODO DE PAGAMENTO POR CHEQUE ASSOCIADO A " + this.nome);
        }else{
            System.out.println("METODO DE PAGAMENTO INVALIDO ");
        }
    }
}