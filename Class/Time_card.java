package Class;
import java.time.LocalDate;

public class Time_card {

    private LocalDate date;
    private int time_in;
    private int time_out;

    public Time_card(int time_in, int time_out){
        this.time_in = time_in;
        this.time_out = time_out;
        this.date = LocalDate.now();
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void setTime_in(int time_in) {
        this.time_in = time_in;
    }
    public void setTime_out(int time_out) {
        this.time_out = time_out;
    }
    public LocalDate getDate() {
        return date;
    }
    public int getTime_in() {
        return time_in;
    }
    public int getTime_out() {
        return time_out;
    }    
}
