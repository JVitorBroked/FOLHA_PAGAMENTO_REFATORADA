package Class;

import java.time.LocalDate;

import Interfaces.Schedule;

public class ScheduleCustom implements Schedule {

    String schedule;
    public ScheduleCustom(String schedule){
        this.schedule = schedule;
    }
    @Override
    public LocalDate processarSchedule() {
        // TODO Auto-generated method stub
        return null;
    }
    
    
}
