package src;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.UUID;

public class principal {
    
    public static void main(String[] args) {

        UUID id = UUID.randomUUID();
        System.out.println(id);
        

        //Proximo final de mes
        /* LocalDate next_day_to_pay = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth().plus(1), 1).minusDays(1);
         
        if(next_day_to_pay.getDayOfWeek().name().equals("SUNDAY")){
            next_day_to_pay = next_day_to_pay.minusDays(2);
        }else if(next_day_to_pay.getDayOfWeek().name().equals("SATURDAY")){
            next_day_to_pay = next_day_to_pay.minusDays(1);
        } 
        System.out.println(next_day_to_pay);
         */
        //Proximo dia pagamento toda sexta 
        /* LocalDate date_now = LocalDate.now();
        while(!(date_now.getDayOfWeek().name().equals("FRIDAY"))){
            System.out.println(date_now);
            date_now = date_now.plusDays(1);
        }

        LocalDate next_day_to_pay = date_now;
        System.out.println("Next day to pay: " +  next_day_to_pay);
         */


        //Pagamento 2 sextas
        /* LocalDate date_now = LocalDate.now();
        int weeks = 0;
        while(!(date_now.getDayOfWeek().name().equals("FRIDAY")) || weeks < 1){
            System.out.println(date_now);
            if(date_now.getDayOfWeek().name().equals("FRIDAY")){
                weeks++;
            }
            date_now = date_now.plusDays(1);
        }
        LocalDate next_day_to_pay = date_now;
        System.out.println("Next day to pay: " +  next_day_to_pay);
         */
    }   
}