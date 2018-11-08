package lessons2.lesson_6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server  {
  ServerSocket SS = null;
  Socket sockA = null;
  DataInputStream DIS = null;
  DataOutputStream DOS = null;


  void start() {
      try {
        SS = new ServerSocket(9000);
        while (!SS.isClosed()) {
          sockA = SS.accept();
          System.out.println("соединение получено");
          DIS = new DataInputStream(sockA.getInputStream());
          DOS = new DataOutputStream(sockA.getOutputStream());
          Thread inR = new Thread(new Reader(DIS));
          Thread outW = new Thread(new Writer(DOS));
          inR.start();
          outW.start();
          inR.join();
          outW.join();
          return;
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
  }

  public static void main(String[] args) {
    Server ser1 = new Server();
    ser1.start();
  }
}
