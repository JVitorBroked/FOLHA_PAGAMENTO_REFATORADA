package Class;
import java.time.LocalDate;

public class Union_service {
    
    private String service;
    private double taxe;
    private LocalDate date;

    public Union_service(String service, double taxe, LocalDate date){
        this.service = service;
        this.taxe = taxe;
        this.date = date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void setService(String service) {
        this.service = service;
    }
    public void setTaxe(double taxe) {
        this.taxe = taxe;
    }
    public LocalDate getDate() {
        return date;
    }
    public String getService() {
        return service;
    }
    public double getTaxe() {
        return taxe;
    }
}
