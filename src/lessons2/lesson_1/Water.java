package lessons2.lesson_1;

public class Water extends Obstacle {
    int len;

    public Water(int len) {
        this.len = len;
    }

    @Override
    public void doit(Competitor competitor) {
        competitor.swim(len);
    }
}
