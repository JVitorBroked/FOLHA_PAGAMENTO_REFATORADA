package Class;
import java.util.ArrayList;

public class Hourly extends Employee {

    private double hour_rate;
    private ArrayList<Time_card> times_cards;

    public Hourly(String nome, String endereco, double hour_rate){
        super(nome, endereco);
        super.addPaymentSchedule(new ScheduleWeekly());
        this.hour_rate = hour_rate;
        this.times_cards = new ArrayList<Time_card>();
    }

    public void setHour_rate(double hour_rate) {
        this.hour_rate = hour_rate;
    }
    public double getHour_rate() {
        return hour_rate;
    }
    
    public void add_time_card(int time_in, int time_out){

        this.times_cards.add(new Time_card(time_in, time_out));

    }
    public double calculate_commission(){
        
        double value = 0;
        for (Time_card time_card : times_cards) {
            value += (time_card.getTime_out() - time_card.getTime_in()) * this.getHour_rate();           
        }
        return value;
    }
    public void clear_time_cards(){
        this.times_cards.clear();
    }

    public void list_time_card(){

        for (Time_card time_card : times_cards) {
            System.out.println("Data: " + time_card.getDate() + "\ntime_in: "+ time_card.getTime_in() + "\ntime_out: " + time_card.getTime_out());           
        }
    }

    @Override
    public double valueOfPayment() {
 
        double value = calculate_commission();
        clear_time_cards();
        return value;
    }
}