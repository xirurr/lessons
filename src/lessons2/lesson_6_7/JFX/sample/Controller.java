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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
  public Button regBtn;
  public TextField login;
  public PasswordField password;
  Socket sockA = null;
  DataInputStream DIS = null;
  DataOutputStream DOS = null;
  String myname;
  String var;

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
        if (txt.getText().indexOf("WRONG USERNAME") == 0) {
          String var = txt.getText().replace("WRONG USERNAME", "");
          showAlert("WRONG USERNAME", "THERE IS NO SUCH USER :" + var);
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

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    try {
      sockA = new Socket("127.0.0.1", 9000);
      System.out.println("Я подключился");
      DIS = new DataInputStream(sockA.getInputStream());
      DOS = new DataOutputStream(sockA.getOutputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
