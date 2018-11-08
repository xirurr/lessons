package lessons2.lesson_6;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Writer implements Runnable  {
  DataOutputStream DOS;
  public Writer(DataOutputStream DOS) {
    this.DOS = DOS;
  }

  private void outWriter() throws IOException {
    while (true){
      Scanner in = new Scanner(System.in);
      String text = in.next();
      DOS.writeUTF(text);
    }
  }

  @Override
  public void run()  {
    try {
      outWriter();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
