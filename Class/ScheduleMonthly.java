package Class;

import java.time.LocalDate;

import Interfaces.Schedule;

public class ScheduleMonthly implements Schedule{

    @Override
    public LocalDate processarSchedule() {
    
        LocalDate next_day_to_pay = LocalDate.of(LocalDate.now().getYear(), 
                                            LocalDate.now().getMonth().plus(1), 1).minusDays(1); 

        if(next_day_to_pay.getDayOfWeek().name().equals("SUNDAY")){
            next_day_to_pay = next_day_to_pay.minusDays(2);
        }else if(next_day_to_pay.getDayOfWeek().name().equals("SATURDAY")){
            next_day_to_pay = next_day_to_pay.minusDays(1);
        } 
        return next_day_to_pay;
    }
}
