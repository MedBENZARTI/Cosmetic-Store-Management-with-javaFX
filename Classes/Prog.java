package Classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// import Interfaces.*;

public class Prog {

    public static void main(String[] args)  {
        LocalDateTime myDateObj = LocalDateTime.now();
        System.out.println("Before formatting: " + myDateObj);
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("YYYY-MM-DD HH:MI:SS");
    
        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println("After formatting: " + formattedDate);
}

}