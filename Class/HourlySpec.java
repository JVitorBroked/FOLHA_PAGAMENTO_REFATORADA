package Class;
import Interfaces.Specification;

public class HourlySpec implements Specification {

    @Override
    public boolean isSatisfied(Employee employee){
        if(employee instanceof Hourly){
            return true;
        }
        return false;
    }

    
}
