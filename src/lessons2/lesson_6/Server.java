package lessons2.lesson_6;

import java.io.*;
import java.net.Socket;
import java.sql.*;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Server implements Runnable {
  Socket sockA;
  DataInputStream DIS;
  DataOutputStream DOS;
  static List<CClient> connectionList = new Vector<>();

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
        System.out.println(var);
        Matcher m = p.matcher(var);
        if (m.matches()) {
          String[] loginpwd = var.split("!84AUTH");
          String login = loginpwd[1];
          String pwd = loginpwd[2];
          if (!auth(login, pwd)) {
            DOS.writeUTF("tries left: " + (i - 1));
          }
          else return true;
          if (i - 1 == 0) {
            DOS.writeUTF("bb");
            System.exit(0);
          }
        }
      }
    }
  }

  boolean auth(String login, String pwd) {
    try {
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection("jdbc:sqlite:D://YandexDisk//GIT//lesson_4//mainDB.db");
      stmt = connection.createStatement();
      String sql = String.format("SELECT nickname FROM account" +
              " WHERE name = '%s' AND pwd = '%s'", login, pwd);
      try {
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
          DOS.writeUTF(rs.getString(1) + " autorized");
          connectionList.add(new CClient(sockA, rs.getString(1)));
          return true;
        } else {
          DOS.writeUTF("u are not authorized");
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
      connectionList.forEach(o -> {
        try {
          o.getDOS().writeUTF(txt);
        } catch (IOException e) {
          e.printStackTrace();
        }
      });
    }
  }

  @Override
  public void run() {
    try {
      if (checkAUTH())
        while (!sockA.isClosed()){
        broadCast();}
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
