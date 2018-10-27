package lessons2.lesson_3;

import java.util.HashMap;
import java.util.Map;

public class Words {
    public static void main(String[] args) {
        String[] words = {"hello", "buy", "bye", "top", "low", "lingue", "buy", "bye", "hello", "hello", "low", "low", "during", "turing", "ebony", "goodbyy", "lord"};
        System.out.println(countSinglWords(words));
    }

    static Map countSinglWords(String[] words) {
        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            Integer current = wordMap.get(word);
            wordMap.put(word, current == null ? 1 : current + 1);
        }
        return wordMap;
    }
}
