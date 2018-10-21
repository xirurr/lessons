package lessons2.lesson_1;

public abstract class Animal implements Competitor {

    String type;
    String name;

    int maxRunDistance;
    int maxSwimDistance;
    int maxJumpHeight;

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    boolean onDistance;

    public Animal(String type, String name, int maxRunDistance, int maxSwimDistance, int maxJumpHeight) {
        this.type = type;
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxSwimDistance = maxSwimDistance;
        this.maxJumpHeight = maxJumpHeight;
        this.onDistance = true;
    }

    @Override
    public void run(int dist) {

        if (onDistance) {
            if (dist <= maxRunDistance) {
                System.out.println(type + " " + name + " справился с кроссом");
            } else {
                System.out.println(type + " " + name + " не справился с кроссом");
                onDistance = false;
            }
        }
    }

    @Override
    public void swim(int dist) {
        if (onDistance) {
            if (dist <= maxSwimDistance) {
                System.out.println(type + " " + name + " справился с заплывом");
            } else {
                System.out.println(type + " " + name + " не справился с заплывом");
                onDistance = false;
            }
        }
    }

    @Override
    public void jump(int height) {
        if (onDistance) {
            if (height <= maxJumpHeight) {
                System.out.println(type + " " + name + " справился с прыжком");
            } else {
                System.out.println(type + " " + name + " не справился с прыжком");
                onDistance = false;
            }
        }
    }

    @Override
    public boolean isOnDistance() {
        return onDistance;
    }

    @Override
    public void info() {
        if (onDistance) {
            System.out.println(type + " " + name + " " + "справился с марафоном");
        } else {
            System.out.println(type + " " + name + " " + "не справился с марафоном");
        }

    }
}
