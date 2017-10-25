package gui;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;



public class JWorksGUI extends Application {
  private Stage stage;

  @Override
  public void start(Stage arg0) throws Exception {
    Scene scene = new Scene(new AnchorPane());
    
    LoginManager loginManager = new LoginManager(scene);
    loginManager.showLoginScreen();
    
    this.stage = arg0;
    stage.setTitle("JWorks");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
