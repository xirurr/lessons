package lessons2.lesson_6_7;

import java.io.*;
import java.net.Socket;

public class CClient {
  Socket sockA;
  String name;
  DataOutputStream DOS;
  DataInputStream DIS;

  public Socket getSockA() {
    return sockA;
  }

  public String getName() {
    return name;
  }

  public DataOutputStream getDOS() {
    return DOS;
  }

  public DataInputStream getDIS() {
    return DIS;
  }

  public CClient(Socket sockA, String name) {
    this.sockA = sockA;
    this.name = name;
    try {
      this.DIS = new DataInputStream(sockA.getInputStream());
      this.DOS = new DataOutputStream(sockA.getOutputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }


  }
}
