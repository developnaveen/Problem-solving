import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
public class dateandtime{
    public static void main(String[] args){

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        Instant now = Instant.now();
        LocalDateTime dt = LocalDateTime.now();
        LocalDate ld = LocalDate.of(2025, Month.MARCH, 5);
        System.out.println("date is: "+date+" time is: "+time+" time: "+ now+"the date and time is "+dt+ " new time "+ld);
    }
}