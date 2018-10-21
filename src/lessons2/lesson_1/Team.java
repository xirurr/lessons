package lessons2.lesson_1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Team {
    List<Competitor> competitors = new ArrayList<>();

    public Team(Competitor... a) {
        if (a.length != 4) {
            System.out.println("неверное число участников");
        } else {
            for (Competitor var : a) {
                competitors.add(var);
            }
        }
    }

    void marathonNOW(List<Obstacle> obst) {
        competitors.forEach(comp -> obst.forEach(o -> o.doit(comp)));
    }

    void howItGoes() {
        competitors.forEach(comp -> comp.info());
    }

    void whoComplete() {
        List<Competitor> var;
        var = competitors.stream().filter(o -> o.isOnDistance()).collect(Collectors.toCollection(ArrayList::new));
        if (var.size() > 0) {
            System.out.print("Справились с марафоном: ");
            var.forEach(o -> System.out.println(o.getType() + " " + o.getName()));
        }
    }


}
