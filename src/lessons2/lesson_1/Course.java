package lessons2.lesson_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Course {
    List<Obstacle> obstacles = new ArrayList<>();

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public Course(List<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    public Course(Obstacle... a) {
        this.obstacles.addAll(Arrays.asList(a));
    }

    public Course() {
        int ObstCount = (int) ((Math.random() * 10) + 1);
        for (int i = 0; i < ObstCount; i++) {
            switch ((int) (Math.random() * 3)) {
                case 0:
                    obstacles.add(new Cross((int) (Math.random() * 80) + 10));
                    break;
                case 1:
                    obstacles.add(new Wall((int) (Math.random() * 5) + 1));
                    break;
                case 2:
                    obstacles.add(new Water((int) (Math.random() * 8) + 1));
                    break;
            }
        }
    }


    void runIt(Team t1) {
        t1.marathonNOW(obstacles);
    }

    void runIt(Competitor... competitiors) {
        for (Competitor c1 : competitiors) {
            obstacles.forEach(o -> o.doit(c1));
        }
    }

}
