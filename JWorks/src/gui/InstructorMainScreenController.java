package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.FXML;

public class InstructorMainScreenController {
  @FXML
  private Button logoutButton;
  @FXML
  private Label welcomeLabel;
  @FXML
  private Button createNewQuestionButton;
  @FXML
  private Button viewQuestionsButton;
  
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
