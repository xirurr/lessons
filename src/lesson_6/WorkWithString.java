package lesson_6;

import java.util.ArrayList;

public class WorkWithString {
    public static void main(String[] args) {
        String str1 = " Предложение один    Теперь предложение два     Предложение три";
        System.out.println(str1);
        ArrayList<String> strings = new ArrayList<>();
        for (String var:str1.split(" +")){
            var.replace(" ","");
            if (!var.equals("")){
            strings.add(var);}
        }
        System.out.println(strings);

        ArrayList<String> stringlowcase = new ArrayList<>();
        strings.forEach(string -> stringlowcase.add(string.toLowerCase()));
        System.out.println(stringlowcase);

        ArrayList<Integer> higherCasePosition = new ArrayList<>();
        for (int i = 0; i < strings.size() ; i++) {
            if (!stringlowcase.get(i).equals(strings.get(i))){
                higherCasePosition.add(i);
            }
        }
        System.out.println(higherCasePosition);
        
        for (int i = 0; i < stringlowcase.size() ; i++) {
            System.out.print(strings.get(i));
            if (higherCasePosition.contains(i+1)){
                System.out.print(". ");
            }
            else if(i+1==strings.size()){
                System.out.print(".");
            }
            else{
                System.out.print(" ");
            }
        }
    }
}
