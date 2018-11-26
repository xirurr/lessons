package lessons3.lesson_1;

public class Main {
  public static void main(String[] args) {
    Box<Orange> bo1 = new Box<>();
    Box<Apple> ba1 = new Box<>();

    for (int i = 0; i < 10; i++) {
      bo1.add(new Orange());
    }
    for (int i = 0; i < 11; i++) {
      ba1.add(new Apple());
    }
   testI(bo1, c -> bo1.getWeight()==c.getWeight());


  }

  public static void testI(Box box,CheckBox checkBox ){
    if (checkBox.test(box)){
      System.out.println("ONI RAVNY");
    }
  }



}
