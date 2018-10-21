package lesson1.lesson_3_1;

import java.util.Scanner;

public class HomeWork3_1 {
    public static void main(String[] args) {
        new HomeWork3_1().start();
    }
    void start (){
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        String secretWord = words[(int)(Math.random()*(words.length-1))];
        System.out.println(secretWord);
        System.out.println("введите загаданное слово");
        Scanner in = new Scanner(System.in);
        String prediction;
        StringBuilder result = new StringBuilder("###############");
while (!(prediction = in.next()).equals(secretWord)) {
    System.out.println("не угадал. подсказака:");

    for (int i = 0; i < Math.min(prediction.length(), secretWord.length()); i++) {
        if (secretWord.toLowerCase().charAt(i) == prediction.toLowerCase().charAt(i)) {
            result.setCharAt(i, secretWord.charAt(i));
        }
    }
    System.out.println(result);
    System.out.println("введите загаданное слово");
}
        System.out.println("угадал");
    }
}
