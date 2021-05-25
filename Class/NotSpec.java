package Class;
import Interfaces.Specification;

public class NotSpec implements Specification{

    @Override
    public boolean isSatisfied(Employee employee) {
        return true;
    }
}
