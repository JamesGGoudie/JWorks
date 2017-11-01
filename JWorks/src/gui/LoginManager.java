package gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class LoginManager {
  private Scene scene;

  public LoginManager(Scene scene) {
    this.scene = scene;
  }

  public void Login(String user) {
    ShowInstructorMainScreen(user);
  }

  public void logout() {
    showLoginScreen();
  }

  public void showLoginScreen() {
    try {
      FXMLLoader loader =
          new FXMLLoader(getClass().getResource("LoginScreen.fxml"));
      scene.setRoot((Parent) loader.load());
      LoginController controller = loader.<LoginController>getController();
      controller.initManager(this);
    } catch (IOException ex) {
      Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null,
          ex);
    }
  }

  private void ShowInstructorMainScreen(String user) {
    try {
      InstructorMainScreenManager instructorMainScreenManager =
          new InstructorMainScreenManager(this.scene);
      FXMLLoader loader =
          new FXMLLoader(getClass().getResource("InstructorMainScreen.fxml"));
      scene.setRoot((Parent) loader.load());
      InstructorMainScreenController controller =
          loader.<InstructorMainScreenController>getController();
      controller.initSession(this, instructorMainScreenManager, user);
    } catch (IOException ex) {
      Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null,
          ex);
    }
  }
}
