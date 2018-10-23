package lessons2.lesson_2;

public class enumWork {
    public static void main(String[] args) {
        System.out.println(hoursOfTheDay(Day.SAT));
    }
  static  String hoursOfTheDay(Day toDay){
        if ((5-toDay.ordinal())*8>0){
            return String.valueOf((5-toDay.ordinal())*8);
        }
        else return "WEEKEND";
    }
}
