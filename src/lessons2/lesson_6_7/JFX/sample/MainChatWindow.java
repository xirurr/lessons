package lessons2.lesson_6_7.JFX.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainChatWindow extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
    Parent root = loader.load();
    Scene scen = new Scene(root, 600, 400);
    primaryStage.setScene(scen);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
