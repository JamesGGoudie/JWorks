package gui;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class StudentMainScreenManager extends Manager {
  private Scene scene;
  private StudentMainScreenController controller;
  private StudentInnerScreenManager studentInnerScreenManager =
      new StudentInnerScreenManager();

  public StudentMainScreenManager(Scene scene) {
    this.scene = scene;
  }

  public void home(Pane innerPane) {
    showHomeScreen(innerPane);
  }

  public void showScreen(LoginManager loginManager, String user) {
    loader = loadNewScreen(loader, scene, "StudentMainScreen.fxml");
    // load the controller of the screen
    controller = loader.<StudentMainScreenController>getController();
    // set the user name for
    controller.setUserName(user);
    controller.setLoginManager(loginManager);
    // Start the controller
    controller.start(this);
  }

  private void showHomeScreen(Pane innerPane) {
    studentInnerScreenManager.showScreen(innerPane);
  }
}
