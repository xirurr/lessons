package lessons2.lesson_6_7;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.sql.*;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Server implements Runnable {
  Socket sockA;
  DataInputStream DIS;
  DataOutputStream DOS;
  static List<CClient> connectionList = new Vector<>();
  static List<String> connectionListNames = new Vector<>();
  String name;
  CClient connectedClient = null;
  private static Connection connection;
  private static Statement stmt;


  public Server(Socket s) throws IOException {
    sockA = s;
    DIS = new DataInputStream(sockA.getInputStream());
    DOS = new DataOutputStream(sockA.getOutputStream());
  }

  boolean checkAUTH() throws Exception {
    Pattern p = Pattern.compile("^!84AUTH.+$");
    while (true) {
      for (int i = 3; i > 0; i--) {
        String var = DIS.readUTF();
        Matcher m = p.matcher(var);
        if (m.matches()) {
          String[] loginpwd = var.split("!84AUTH");
          String login = loginpwd[1];
          String pwd = loginpwd[2];
          if (!auth(login, pwd)) {
            DOS.writeUTF("!tries left: " + (i - 1));
          } else return true;
          if (i - 1 == 0) {
            DOS.writeUTF("!bb");
            sockA.close();
            return false;
          }
        }
      }
    }
  }

  boolean auth(String login, String pwd) {
    try {
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection("jdbc:sqlite:mainDB.db");
      stmt = connection.createStatement();
      String sql = String.format("SELECT nickname FROM account" +
              " WHERE name = '%s' AND pwd = '%s'", login, pwd);
      try {
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
          if (connectionListNames.contains(rs.getString(1))) {
            DOS.writeUTF("USER ALREADY AUTHORIZED");
            return false;
          }
          DOS.writeUTF("!authorized " + rs.getString(1));
          name = rs.getString(1);
          connectedClient = new CClient(sockA, rs.getString(1));
          connectionListNames.add(rs.getString(1));
          connectionList.add(connectedClient);
          connectionListNames.sort(String::compareTo);
          sysBroadBast("/NEWUSER"+connectionListNames);
          return true;
        } else {
          DOS.writeUTF("!u are not authorized");
          return false;
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  void broadCast() throws IOException {
    while (true) {
      String txt = DIS.readUTF();
      if (txt.indexOf("/w ") == 0 ) {
        whisper(txt);
      }
      else if (txt.indexOf("/NEWUSER")==0){
        continue;
      }

      else
        connectionList.forEach(o -> {
          try {
            o.getDOS().writeUTF(name + ":" + txt);
          } catch (IOException e) {
            e.printStackTrace();
          }
        });
    }
  }
  void sysBroadBast(String txt) throws IOException {
        connectionList.forEach(o -> {
          try {
            o.getDOS().writeUTF(txt);
          } catch (IOException e) {
            e.printStackTrace();
          }
        });
  }

  void whisper(String whispTxt) {
    whispTxt = whispTxt.replaceAll("\\s+", " ");
    String pName = whispTxt.split(" ")[1];
    String txt = whispTxt.replace("/w ", "");
    txt = txt.replace(pName, "");
    String finalTxt = txt;
    final CClient[] recieverAndSender = new CClient[2];
    connectionList.stream().filter(o -> o.getName().equals(pName)).forEach(c -> {
      if (!c.getSockA().isClosed()) {
        recieverAndSender[0] = c;
      }
    });
    connectionList.stream().filter(o -> o.getName().equals(name)).forEach(c -> {
      recieverAndSender[1] = c;
      if (recieverAndSender[0] != null) {
        try {
          recieverAndSender[0].getDOS().writeUTF("PRIVATE " + name + " writes to YOU: " + finalTxt);
          c.getDOS().writeUTF("YOU write to " + pName + finalTxt);
        } catch (IOException e) {
          e.printStackTrace();
        }

      } else {
        try {
          c.getDOS().writeUTF("/WRONGUSERNAME" + pName);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
  }

  @Override
  public void run() {
    try {
      if (checkAUTH()) {
        while (!sockA.isClosed()) {
          broadCast();
        }
      }
    } catch (SocketException se) {
      System.out.println("соединение завершено");
      if (connectionList.contains(connectedClient)) {
        connectionList.remove(connectedClient);
        connectionListNames.remove(name);

        System.out.println("клиент удален");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
