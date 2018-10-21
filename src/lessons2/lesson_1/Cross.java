package lessons2.lesson_1;

public class Cross extends Obstacle {
    int len;

    public Cross(int len) {
        this.len = len;
    }

    @Override
    public void doit(Competitor competitor) {
        competitor.run(len);
    }
}
