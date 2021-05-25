package Class;

import java.time.LocalDate;

import Interfaces.Schedule;

public class ScheduleWeekly implements Schedule{

    public LocalDate processarSchedule(){

        LocalDate date_now = LocalDate.now();
        if(date_now.getDayOfWeek().name().equals("FRIDAY")){
            date_now = date_now.plusDays(1);
        }
        while(!(date_now.getDayOfWeek().name().equals("FRIDAY"))){
            date_now = date_now.plusDays(1);
        }
        
        return date_now;
    }

}