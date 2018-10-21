package Lesson_1.Marathon;

public class MainMarathon {
    public static void main(String[] args) {
        Competitor[] competitors = {new Cat("Барсик"), new Dog("Бобик")};
        Obstacle[] obstacles = {new Cross(80), new Wall(2), new Water(10)};

        for (Competitor c: competitors) {
            for (Obstacle o: obstacles) {
                o.doit(c);
                if(!c.isOnDistance()) {
                    break;
                }
            }
        }
        for (Competitor c: competitors) {
            c.info();
        }
    }
}
