package Class;
public class Salaried extends Employee{
    
    private double salary;

    public Salaried(String nome, String endereco, double salary){
        super(nome, endereco);
        super.addPaymentSchedule(new ScheduleMonthly());
        this.salary = salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public double valueOfPayment() {
        return getSalary();
    }
}
