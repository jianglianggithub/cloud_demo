import javax.swing.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.ArrayList;

public class A {
    public static void main(String[] args) {
        Temporal startTime= LocalDateTime.of(2020,1,1,12,25,25);
        Temporal endTime = LocalDateTime.of(2020,1,1,12,24,25);
        long l = Duration.between(startTime, endTime).toMinutes();
        System.out.println(l);


    }
}
