package lesson1.lesson_3_1;

import java.util.ArrayList;
import java.util.Scanner;


public class calculator {
    ArrayList<Double> numbers = new ArrayList<>();
    ArrayList<String> operators = new ArrayList<>();
    public static void main(String[] args) {
        new calculator().main();
    }
    void main(){
    System.out.println("введите выражение");
    collectIT();
    calcIt();
        System.out.println("ответ:");
        System.out.println(numbers);
    }
    void calcIt(){
        while (numbers.size()>1){
            for (int i=0; i<operators.size();i++){
                switch (operators.get(i)){
                    case "*": multiplication(i);
                        i--;
                        break;
                    case "/": division(i);
                        i--;
                        break;
                }
            }
            for (int i=0; i<operators.size();i++){
                switch (operators.get(i)){
                    case "+": addition(i);
                        i--;
                        break;
                    case "-": subtraction(i);
                        i--;
                        break;
                }
            }
        }
    }
    void collectIT (){
        Scanner in = new Scanner(System.in);
        String expresssion = in.nextLine();
        expresssion = clearSpaces(expresssion);
        for (String var:expresssion.split("\\W")){
            numbers.add(Double.parseDouble(var));
        }
        for (String var:expresssion.split("\\d")){
            operators.add(var);
            operators=clearSpaces(operators);
        }
    }
    String clearSpaces(String exp){
        exp = exp.replace(" ","");
        return exp;
    }
    ArrayList<String>clearSpaces(ArrayList<String> var){
        for (int i=0; i<var.size();i++){
            if (var.get(i).equals("")||var.get(i).equals("")){
                var.remove(i);
            }
        }
        return var;
    }
    void multiplication(int pos){
        Double var = numbers.get(pos)*numbers.get(pos+1);
        numbers.remove(pos);
        numbers.remove(pos);
        numbers.add(pos,var);
        operators.remove(pos);
     /*   System.out.println(var);
        System.out.println(numbers);
        System.out.println(operators);*/
    }
    void division(int pos){
        Double var = numbers.get(pos)/numbers.get(pos+1);
        numbers.remove(pos);
        numbers.remove(pos);
        numbers.add(pos,var);
        operators.remove(pos);
      /*  System.out.println(var);
        System.out.println(numbers);
        System.out.println(operators);*/
    }
    void addition(int pos){
        Double var = numbers.get(pos)+numbers.get(pos+1);
        numbers.remove(pos);
        numbers.remove(pos);
        numbers.add(pos,var);
        operators.remove(pos);
  /*      System.out.println(var);
        System.out.println(numbers);
        System.out.println(operators);*/
    }
    void subtraction(int pos){
        Double var = numbers.get(pos)-numbers.get(pos+1);
        numbers.remove(pos);
        numbers.remove(pos);
        numbers.add(pos,var);
        operators.remove(pos);
   /*     System.out.println(var);
        System.out.println(numbers);
        System.out.println(operators);*/
    }
}
