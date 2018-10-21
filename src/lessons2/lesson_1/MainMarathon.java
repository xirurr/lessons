package lessons2.lesson_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainMarathon {
    public static void main(String[] args) {
        //   List<Obstacle> obstacles =new ArrayList<>(Arrays.asList(new Cross(80),new Wall(2), new Water(10)));
        Course set1 = new Course();
        Team team1 = new Team(new Cat("Барсик"), new Dog("Бобик"), new Cat("Kitty"), new Dog("Doggy"));
        Team team2 = new Team(new Cat("Anton2"), new Dog("SObaka2"), new Cat("Kotopes2"), new Dog("NeSobaka2"));


        team1.marathonNOW(set1.getObstacles());
        set1.runIt(team2);


        team1.howItGoes();
        team2.howItGoes();
        team1.whoComplete();
        team2.whoComplete();
    }
}
