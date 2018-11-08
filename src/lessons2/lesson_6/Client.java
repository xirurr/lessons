package lessons2.lesson_6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client {
  public static void main(String[] args) {
    Client cl1 = new Client();
    cl1.start();
  }
  Socket sockA = null;
  DataInputStream DIS = null;
  DataOutputStream DOS = null;


  void start(){
    try {
      sockA = new Socket("127.0.0.1",9000);
      while (!sockA.isClosed()) {
        System.out.println("Я подключился");
        DIS = new DataInputStream(sockA.getInputStream());
        DOS = new DataOutputStream(sockA.getOutputStream());
        Thread inR = new Thread(new Reader(DIS));
        Thread outW = new Thread(new Writer(DOS));
        inR.start();
        outW.start();
        inR.join();
        outW.join();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
