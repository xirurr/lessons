package lessons2.lesson_3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Phone extends HashMap {
    public static void main(String[] args) {
        Map<String, ArrayList<Integer>> phoneBook1 = new Phone();

        ((Phone) phoneBook1).add("egorov", 999123);
        ((Phone) phoneBook1).add("egorov", 998143);
        ((Phone) phoneBook1).add("egorov", 998111);
        ((Phone) phoneBook1).add("olegov", 363659);
        ((Phone) phoneBook1).add("nikifor", 983235);
        ((Phone) phoneBook1).add("egorov", 1359866);


        System.out.println(((Phone) phoneBook1).get("egorov"));
    }


    void add(String surname, Integer number) {
        ArrayList<Integer> numberMassive;
        if ((ArrayList<Integer>) this.get(surname) == null) numberMassive = new ArrayList<>();
        else
            numberMassive = (ArrayList<Integer>) this.get(surname);
        numberMassive.add(number);
        this.put(surname, numberMassive);
    }
}



