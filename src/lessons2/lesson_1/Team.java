package lessons2.lesson_1;

import java.util.ArrayList;
import java.util.List;

public class Team {
    List<Competitor> competitors = new  ArrayList<>();
   public Team(Competitor ... a){
        if (a.length!=4){
            System.out.println("неверное число участников");
        }
        else{
            for (Competitor var:a){
                competitors.add(var);
            }
        }
    }


}
