package lessons3.lesson_1;

import javafx.collections.ArrayChangeListener;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class Box<T extends Fruit> {
  List<T> box1 = new ArrayList<>();
  void add(T var) {
    box1.add(var);
  }
  float getWeight() {
    return box1.size() * (1.0f);
  }
  boolean compare(Box boxToCompare) {
    return boxToCompare.getWeight() == this.getWeight();
  }


  boolean compareByPredicate(Box boxToCompare){
    BiPredicate<Box,Box> pred = (s,s1)-> s.getWeight()==s1.getWeight();
    return pred.test(this,boxToCompare);
  }

}
