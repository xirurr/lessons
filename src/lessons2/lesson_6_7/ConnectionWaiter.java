package lessons2.lesson_6_7;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionWaiter {
  static ExecutorService executeIt = Executors.newFixedThreadPool(8);

  public static void main(String[] args) {
    startServ();
  }

  static void startServ() {
    try (ServerSocket SS = new ServerSocket(9000)) {
      while (!SS.isClosed()) {
        Socket sockA = SS.accept();
        System.out.println("соединение получено");
        executeIt.execute(new Server(sockA));
        System.out.println("поток создан");
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

}
