package lesson_6;
public class HomeWork_6 {
    public static void main(String[] args) {
        new HomeWork_6().start();
    }
    void start(){
    Cat cat1 = new Cat();
    Dog dog4 = new Dog();
    Cat cat2 = new Cat();
    Dog dog3 = new Dog();

    cat1.run(30);
    cat2.swim(90);
    dog4.jumpOver(5);
    dog3.run(7);
    }
}

abstract class Animal{
    void run(double range){
        if (runMaxLength-range>=0){
            System.out.println(true);
        }
        else {
            System.out.println(false);
        }
    }
    void swim(double range){
        if (swimMaxLengt-range>=0){
            System.out.println(true);
        }
        else {
            System.out.println(false);
        }
    }
    void jumpOver(double range){
        if (jumpMaxLength-range>=0){
            System.out.println(true);
        }
        else {
            System.out.println(false);
        }
    }
    double jumpMaxLength;
    double runMaxLength;
    double swimMaxLengt;
}
class Cat extends Animal {
    public Cat(){
        jumpMaxLength = 2+Math.random()*2;
        runMaxLength = 200+Math.random()*100;
    }
    @Override
    void swim(double range) {
        System.out.print(false);
        System.out.println("  КОТ НЕ ПЛАВАЕТ!");
    }
}
class Dog extends Animal{
    public Dog(){
        jumpMaxLength = 0.5+Math.random()*2;
        runMaxLength = 500+Math.random()*200;
        swimMaxLengt = 10+Math.random()*4;
    }
}
