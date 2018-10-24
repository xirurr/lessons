package lessons2.lesson_2.enums;

public class Main {
    public static void main(String[] args) {
        System.out.println(hoursToWeekEnd(Day.SAT));
        }

      static String  hoursToWeekEnd(Day toDay) {
          if ((5 - toDay.ordinal()) * 8 > 0) {
              return String.valueOf((5 - toDay.ordinal()) * 8);
          }
          return "WEEKEND";
      }
}
