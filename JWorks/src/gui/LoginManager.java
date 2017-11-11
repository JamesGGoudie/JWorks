package gui;

import javafx.scene.Scene;

public class LoginManager extends Manager {
  private Scene scene;
  private InstructorMainScreenManager instructorMainScreenManager;

  /**
   * Default constructor
   * 
   * @param scene The scene of the screen
   * @param interpreter The interpreter for executing actions
   */
  public LoginManager(Scene scene) {
    this.scene = scene;
  }

  /**
   * Load the corresponding instructor/student screen based on the user's input
   * 
   * @param user The name of the user
   */
  public void Login(String user) {
    ShowInstructorMainScreen(user);
  }

  /**
   * Return the user to the login screen
   */
  public void logout() {
    showScreen();
  }

  /**
   * Display the login screen
   */
  public void showScreen() {
    // load the fxml file that contains the layout of the login screen
    loader = loadNewScreen(loader, scene, "LoginScreen.fxml");

    LoginController controller = loader.<LoginController>getController();
    controller.start(this);
  }

  /**
   * Set the window to show the instructor main screen
   * 
   * @param user The name of the current user
   */
  private void ShowInstructorMainScreen(String user) {
    // create a new instance of instructor main screen manager
    instructorMainScreenManager = new InstructorMainScreenManager(scene, super.interpreter);
    // show the main screen
    instructorMainScreenManager.showScreen(this, user);
  }
}
