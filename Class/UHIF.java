package Class;
import java.util.ArrayList;
import java.util.Scanner;
import Interfaces.Specification;


//UTIL HANDLE INPUT AND FIND METHODS

public class UHIF {

    public static Employee find_Employee(ArrayList<Employee> list_of_Employees, Specification spec,  String name){
        for (Employee employee : list_of_Employees) {
            if(employee.getNome().equals(name) && spec.isSatisfied(employee)){
                return employee;
            }
        }
        return null;
    }
    
    public static int handle_input_int(String out){
        Scanner in = new Scanner(System.in);
        System.out.print(out);
        int value = in.nextInt();
        in.nextLine();
        return value;
    }
    
    public static String handle_input_string(String out){
        Scanner in = new Scanner(System.in);
        System.out.print(out);
        return in.nextLine();
    }

    public static double handle_input_double(String out){
        Scanner in = new Scanner(System.in);
        System.out.print(out);
        double value = in.nextDouble();
        in.nextLine();
        return value;
    }
    public static void handle_interface(){
        Scanner in = new Scanner(System.in);
        System.out.print("TECLE ENTER PARA CONTINUAR");
        in.nextLine();
    }

}
