package lessons2.lesson_1;

public class Cat extends Animal {
    public Cat(String name) {
        super("Кот", name, (int) (Math.random() * 200) + 10, (int) (Math.random() * 20) + 2, (int) (Math.random() * 20) + 2);
    }

}
