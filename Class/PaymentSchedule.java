package Class;
import java.time.LocalDate;

import Interfaces.Schedule;

public class PaymentSchedule{

    private LocalDate last_payment;
    private Schedule schedule;
    private LocalDate nextDatePayment;

    public PaymentSchedule(Schedule schedule){
        this.schedule = schedule;
        this.last_payment = null;
        this.nextDatePayment = Processar_new_date_to_pay();
        
    }

    public Schedule getSchedule() {
        return schedule;
    }
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public LocalDate getNextDatePayment() {
        return nextDatePayment;
    }
    
    public void setNextDatePayment(LocalDate nextDatePayment) {
        this.nextDatePayment = nextDatePayment;
    }

    public LocalDate getLast_payment() {
        return last_payment;
    }
    
    public void setLast_payment(LocalDate last_payment) {
        this.last_payment = last_payment;
    }

    public LocalDate Processar_new_date_to_pay(){
        return schedule.processarSchedule();
    }
}