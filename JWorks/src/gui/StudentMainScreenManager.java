package gui;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class StudentMainScreenManager extends Manager {
  private Scene scene;
  private StudentMainScreenController controller;
  private StudentInnerScreenManager studentInnerScreenManager =
      new StudentInnerScreenManager();

  /**
   * Default constructor
   * 
   * @param scene
   */
  public StudentMainScreenManager(Scene scene) {
    this.scene = scene;
  }

  /**
   * Go to home screen
   * 
   * @param innerPane The pane that needs update
   */
  public void home(Pane innerPane) {
    showHomeScreen(innerPane);
  }

  /**
   * Load and display the main screen for student
   * 
   * @param loginManager
   * @param user The user name
   */
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

  /**
   * Load and display the home screen for student
   * 
   * @param innerPane The pane that needs update
   */
  private void showHomeScreen(Pane innerPane) {
    studentInnerScreenManager.showScreen(innerPane);
  }
}
