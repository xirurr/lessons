package lesson_1;

public class HomeWork {
    public static void main(String[] args) {
        HomeWork hm = new HomeWork();
        hm.createVar();
        hm.calculateFourNumbers(1,2,3,4);
        hm.isFromTentoTwenty(-1,6);
        hm.checkIfPositiv(-10);
        hm.checkIfNegativ(-10);
        hm.showUrName("Эдик");
        hm.checkYear(1603);
    }
    void createVar(){
        byte b1 = 1;
        short s1 = 1111;
        int i1 = 10;
        long l1 = 10L;
        float fl1=10.1F;
        double d1= 10.1;
        char c1='s';
        boolean bool = true;
        String s2 = "String";
    }
    float calculateFourNumbers (float a, float b, float c, float d){
        return a*(b+(c/d));
    }
    boolean isFromTentoTwenty(float a, float b){
        if ((a+b)>=10 &&(a+b)<=20){
            return true;
        }
        return false;
    }
    void checkIfPositiv(int a){
        System.out.println(a >=0 ? "передали положительное": "передали отрицательное");
    }
    boolean checkIfNegativ(int a){
        if (a<=0){
            return true;
        }
        return false;
    }
    void showUrName(String a){
        System.out.println("Привет, "+ a+"!");
    }
    void checkYear(int a){
        if (a%100==0 || a%4>0){
            if (a%400==0){
                System.out.println("год "+a+" високосный");
            }
            else {
                System.out.println("год "+a+" не високосный");
            }
        }
        else {
            System.out.println("год "+a+" високосный");
        }

    }
}
