package Class;
import java.time.LocalDate;
import java.util.ArrayList;

public class Syndicate {

    private int id_employee;
    private double monthly_fee;
    private ArrayList<Union_service> union_services;

    public Syndicate(double monthly_fee){
        this.monthly_fee = monthly_fee;
        this.union_services = new ArrayList<Union_service>();
        //add metodo de random id
    }

    public void setMonthly_fee(double monthly_fee) {
        this.monthly_fee = monthly_fee;
    }

    public double getMonthly_fee() {
        return monthly_fee;
    }

    public void remove_all_services(){
        this.union_services.clear();        
    }

    public double total_value_to_pay(){
        double value = 0;
        for (Union_service union_service : union_services) {
            value += union_service.getTaxe();
        }
        remove_all_services();
        return value;
    }

    public void add_union_service_taxe(String service, double taxe){
        this.union_services.add(new Union_service(service, taxe, LocalDate.now()));
    }
    public void listar_services(){
        if(union_services.size() == 0){
            System.out.println("NAO A SERVICOS ULTILIZADOS");
            return;
        }
        for (Union_service union_service : union_services) {
            System.out.println(union_service.getService() + " : " + union_service.getTaxe() + " : " + union_service.getDate());                      
        }           
    }
}