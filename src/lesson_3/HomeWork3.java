package lesson_3;

import java.util.Scanner;

public class HomeWork3 {

    Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        new HomeWork3().start();
    }
    void start(){
        game();
        int wannamore =1;
        while (wannamore!=0){
            System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
            switch (in.nextInt()){
                case 0:
                    wannamore = 0;
                    break;
                case 1:
                    game();
                    continue;
            }
        }
        }
    void game () {
            System.out.print("введите число от 0 до 9: ");
            int number = (int)(Math.random()*9);
            int tries = 0;
            boolean rightChoise = false;
            d:while (rightChoise != true) {
                if (tries > 2) {
                    System.out.println("Попытки закончились");
                    break d;
                }
                int predict = in.nextInt();
                if (predict < number) {
                    System.out.println("нужно больше");
                    tries++;
                } else if (predict > number) {
                    System.out.println("нужно меньше");
                    tries++;
                } else {
                    rightChoise = true;
                    System.out.println("угадал");
                }
            }
        }
}


