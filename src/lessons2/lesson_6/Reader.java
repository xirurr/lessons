package lessons2.lesson_6;

import java.io.DataInputStream;

import java.io.IOException;


public class Reader implements Runnable  {
  DataInputStream DIS ;

  public Reader(DataInputStream DIS) {
    this.DIS = DIS;
  }

  private void incomeReader() throws IOException {
    while (true){
      String text = DIS.readUTF();
      System.out.println(text);}
  }

  @Override
  public void run()  {
    try {
      incomeReader();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
