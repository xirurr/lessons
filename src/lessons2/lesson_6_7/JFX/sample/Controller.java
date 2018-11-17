package lessons2.lesson_6_7.JFX.sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
  public Button regBtn;
  public TextField login;
  public PasswordField password;
  public ListView userList;
  public MenuItem removeFromBlackListMenuItem;
  ArrayList<String> userListStack;
  Socket sockA = null;
  DataInputStream DIS = null;
  DataOutputStream DOS = null;
  String myname = null;
  String var;
  ArrayList<String> blackList = new ArrayList<>();

  @FXML
  Button bt1;

  @FXML
  ListView outputField;

  @FXML
  TextField inputField;

  @FXML
  HBox authBox;


  @FXML
  public void sendMsg() throws IOException {
    sendNow();
  }

  public void sendFromKey(KeyEvent e) throws IOException {
    if (e.getCode() == KeyCode.ENTER) {
      if (authBox.isVisible()) {
        reg();
      } else sendNow();
    }
  }

  public void sendNow() throws IOException {
    String txt = inputField.getText();
    DOS.writeUTF(txt);
    inputField.clear();
    inputField.requestFocus();
  }

  public void regKey() {
    reg();
  }

  void reg() {
    try {
      DOS.writeUTF("!84AUTH" + login.getText() + "!84AUTH" + password.getText());
      var = DIS.readUTF();
      if (var.indexOf("!authorized") == 0) {
        myname = var.split("!authorized ")[1];
        System.out.println(myname);
        authBox.setVisible(false);
        authBox.setDisable(true);
        outputField.setDisable(false);
        bt1.setDisable(false);
        inputField.setDisable(false);
        userList.setDisable(false);

        reader();
      } else {
        String var2 = DIS.readUTF();
        if (var2.equals("!bb")) System.exit(0);
        if (var.indexOf("USER ALREADY AUTHORIZED") == 0) {
          showAlert("USER IS ALREADY ONLINE", "USER ALREADY AUTHORIZED" + "\n" + var2);
        } else {
          String textAlert = "Wrong username or password!" + var2;
          String typeAlert = "Wrong username/password";
          showAlert(typeAlert, textAlert);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void showAlert(String typeAlert, String txtAlert) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        Alert alrt = new Alert(Alert.AlertType.INFORMATION);
        alrt.setTitle(typeAlert);
        alrt.setContentText(txtAlert);
        alrt.showAndWait();
      }
    });
  }

  void reader() {
    Thread reader = new Thread(() -> {
      while (!sockA.isClosed()) {
        Text txt = null;
        try {
          txt = new Text(DIS.readUTF());
        } catch (IOException e) {
          e.printStackTrace();
        }
        VBox vb = new VBox(txt);
        if (txt.getText().indexOf("/") == 0) {
                    sysMessage(txt.getText());
          continue;

        }
        if (blackList.contains(txt.getText().split(":")[0])) {
          continue;
        }
        if (txt.getText().indexOf(myname) == 0 || txt.getText().indexOf("YOU") == 0) {
          vb.setAlignment(Pos.CENTER_RIGHT);
        }
        Platform.runLater(() -> {
                  outputField.getItems().add(vb);
                }
        );
      }
    });
    reader.setDaemon(true);
    reader.start();
  }

  void sysMessage(String vartxt) {
    String var2 = new String();
    if (vartxt.indexOf("/WRONGUSERNAME") == 0) {
      Platform.runLater(() -> {
                messageWrongUserName(vartxt);
              }
      );
    }
    if (vartxt.indexOf("/NEWUSER") == 0) {
      Platform.runLater(() -> newUser(vartxt)
      );
    }
    if (vartxt.indexOf("/NEWBLACKLIST ") == 0) {
      Platform.runLater(() -> gotNewBlackList(vartxt)
      );
    }
    if (vartxt.indexOf("/ALREADY IN") == 0) {
      Platform.runLater(() -> messageAlreadyInBlack()
      );
    }
    if (vartxt.indexOf("/ALREADY NOT") == 0) {
      Platform.runLater(() -> messageAlreadyNotInBlack()
      );
    }
    if (vartxt.indexOf("/PRIVATE ") == 0) {
      Platform.runLater(() -> gotPrivate(vartxt)
      );
    }
  }

  private void gotNewBlackList(String vartxt) {
    blackList.clear();
    blackList = new ArrayList<>(Arrays.asList(vartxt.replace("/NEWBLACKLIST ", "").split(",")));
    Collections.sort(userListStack);
    Platform.runLater(() -> userList.getItems().remove(0, userList.getItems().size())
    );
    renewUserList();
  }

  private void renewUserList() {
    for (String var : userListStack) {
      Platform.runLater(() -> {
                if (blackList.contains(var)) {
                  Label lb = new Label(var);
                  lb.setTextFill(Color.RED);
                  userList.getItems().add(lb);
                } else userList.getItems().add(var);
              }
      );
    }
  }

  private void gotPrivate(String vartxt) {
    if (blackList.contains(vartxt.split(" ")[1])) {
    } else {
      Text txt = new Text(vartxt);
      VBox vb = new VBox(txt);
      outputField.getItems().add(vb);
    }
  }

  private void messageAlreadyInBlack() {
    showAlert("WRONG USERNAME", "USER ALREADY IN BLACK LIST");
  }

  private void messageAlreadyNotInBlack() {
    showAlert("WRONG USERNAME", "NO SUCH USER IN BLACK LIST");
  }

  private void newUser(String vartxt) {
    String var2;
    var2 = vartxt.replace("/NEWUSER", "");
    var2 = var2.replace("[", "");
    var2 = var2.replace("]", "");
    userListStack = new ArrayList<>(Arrays.asList(var2.split(", ")));
    Collections.sort(userListStack);
    Platform.runLater(() -> userList.getItems().remove(0, userList.getItems().size())
    );
    renewUserList();
  }

  private void messageWrongUserName(String vartxt) {
    String var2;
    var2 = vartxt.replace("/WRONG USERNAME", "");
    showAlert("WRONG USERNAME", "THERE IS NO SUCH USER :" + var2);
  }

  public void mouseToWhisper(MouseEvent e) {
    if (e.getButton() == MouseButton.PRIMARY) {

      wannaWhisper();
    }
  }

  public void wannaWhisper() {
    if (userList.getSelectionModel().getSelectedItem() instanceof Label) {
      String test = ((Label) userList.getSelectionModel().getSelectedItem()).getText();
      inputField.requestFocus();

      inputField.setText("/w " + test + " " + inputField.getText());
      inputField.end();


    } else {
      String test = userList.getSelectionModel().getSelectedItem().toString();
      inputField.requestFocus();

      inputField.setText("/w " + test + " " + inputField.getText());
      inputField.end();

    }
  }

  public void wannaBlackList() {
    try {
      if (userList.getSelectionModel().getSelectedItem() instanceof Label) {
        removeFromBlackListMenuItem.setDisable(true);
      } else {
        removeFromBlackListMenuItem.setDisable(false);
        String test = userList.getSelectionModel().getSelectedItem().toString();
        DOS.writeUTF("/b " + test);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void removeFromBlackList() {
    try {
      if (userList.getSelectionModel().getSelectedItem() instanceof Label) {
        String test = ((Label) userList.getSelectionModel().getSelectedItem()).getText();
        DOS.writeUTF("/br " + test);
      } else {
        String test = (String) userList.getSelectionModel().getSelectedItem().toString();

        DOS.writeUTF("/br " + test);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  Thread TimeToAuth = new Thread(() -> {
    try {
      Thread.sleep(10000);
      if (myname == null) {
        sockA.close();
        authBox.setDisable(true);
        showAlert("TIME ELAPSED", "ВРЕМЯ АВТОРИЗАЦИИ ИСТЕКЛО");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  });

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    try {
      sockA = new Socket("127.0.0.1", 9000);
      TimeToAuth.setDaemon(true);
      TimeToAuth.start();
      System.out.println("Я подключился");
      DIS = new DataInputStream(sockA.getInputStream());
      DOS = new DataOutputStream(sockA.getOutputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
