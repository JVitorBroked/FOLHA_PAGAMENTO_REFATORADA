package Class;
import Interfaces.Specification;

public class CommissionedSpec implements Specification {

    @Override
    public boolean isSatisfied(Employee employee) {
        if(employee instanceof Commissioned){
            return true;
        }
        return false;
    }

    
}
