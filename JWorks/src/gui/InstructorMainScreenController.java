package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.fxml.FXML;

public class InstructorMainScreenController {
  @FXML
  private Button logoutButton;
  @FXML
  private Label welcomeLabel;
  @FXML
  private Pane innerScreen;
  @FXML
  private Button homeButton;

  public void initialize() {}

  public void initSession(final LoginManager loginManager,
      InstructorMainScreenManager instructorMainScreenManager, String user) {
    welcomeLabel.setText("Welcome " + user);
    
    instructorMainScreenManager.homeScreen(innerScreen);
    
    logoutButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        loginManager.logout();
      }
    });
    
    homeButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        instructorMainScreenManager.homeScreen(innerScreen);
      }
    });

  }
}
