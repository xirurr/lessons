package lessons2.lesson_1;

public class Dog extends Animal {
    public Dog(String name) {
        super("Пес", name, (int)(Math.random()*500)+10, (int)(Math.random()*5)+1,(int)(Math.random()*20)+2);
    }
}
