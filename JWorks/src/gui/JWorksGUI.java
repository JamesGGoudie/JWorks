package gui;


import io.OutputGen;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import driver.Interpreter;


public class JWorksGUI extends Application {
  private Interpreter interpreter = new Interpreter(OutputGen.OutputMode.GUI);

  private Stage stage;

  @Override
  public void start(Stage arg0) throws Exception {
    Scene scene = new Scene(new AnchorPane());

    // create and start the login screen
    LoginManager loginManager = new LoginManager(scene, interpreter);
    loginManager.showLoginScreen();

    // show the window
    this.stage = arg0;
    stage.setTitle("JWorks");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
