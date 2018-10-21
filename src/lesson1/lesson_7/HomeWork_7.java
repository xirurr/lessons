package lesson1.lesson_7;


import java.util.ArrayList;

class Cat {
    boolean hungry;
    public int getAppetite() {
        return appetite;
    }
    private String name;
    private int appetite;

    public Cat(String name) {
        hungry = true;
        this.name = name;
        this.appetite = (int)(Math.random()*30);
    }

    public void eat(Plate plate) {
        if (chechFood(plate)) {
            if (plate.decreaseFood(appetite)) {
                hungry = false;
                System.out.println(name + " поел "+ appetite +" и не голоден");
            }
        }
    }
    public boolean chechFood(Plate plate){
        if (plate.getFood()>=this.appetite){
            return true;
        }
        System.out.println("для "+name+" слишком мало еды");
        return false;
    }

}

class Plate{
    public void setFood(int food) {
        if (food<0){
            food*=-1;
        }
        this.food = food;

    }

    private int food;
    public void addFood(int food){
        System.out.println("Добавлено "+food+ " еды");

        setFood(getFood()+food);


    }

    public int getFood() {
        return food;
    }

    public boolean decreaseFood(int n) {
        food -= n;
        if (food<0){
            food*=0;
            return false;
        }
        return true;
    }

    public Plate(int food) {
        if (food<0){
            food*=-1;
        }
        this.food = food;
    }

    public void info() {
        System.out.println("plate " + food);
    }
}


class HomeWork_7 {
    public static void main(String[] args) {
        ArrayList<Cat> cats = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Cat cat = new Cat("cat"+i);
            cats.add(cat);
        }
        Plate plate = new Plate(30);
        cats.forEach(kitty -> kitty.eat(plate));
        plate.info();
        plate.addFood(999);
        plate.info();
    }
}
