package lessons2.lesson_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObstaclesSet {
    List<Obstacle> obstacles = new ArrayList<>();

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public ObstaclesSet(List<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }
    public ObstaclesSet(Obstacle ... a) {
        this.obstacles.addAll(Arrays.asList(a));
    }

    public ObstaclesSet() {
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

}
