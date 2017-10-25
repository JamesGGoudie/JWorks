package gui;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class InstructorMainScreenController {
  @FXML
  private Button logoutButton;
  @FXML
  private Label welcomeLabel;
  
  public void initialize() {}
  
  public void initSession(final LoginManager loginManager, String user) {
    welcomeLabel.setText("Welcome " + user);
    
    logoutButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent event) {
        loginManager.logout();
      }
    });
  }
}
