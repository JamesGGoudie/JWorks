package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CreateNewStudentAccountController {
  @FXML
  private TextField  firstName;
  @FXML
  private TextField  lastName;
  @FXML
  private TextField  studentNumber;
  @FXML
  private TextField  emailAddress;
  @FXML
  private Button submitButton;
  @FXML
  private Label successfulMessage;
  @FXML
  private Label firstNameError;
  @FXML
  private Label lastNameError;
  @FXML
  private Label studentNumberError;
  @FXML
  private Label emailError;
  
  public void start(CreateNewStudentAccountManager createNewStudentAccountManager) {
    
    submitButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        if(!hasEmptyFields()) {
          resetFields();
          successfulMessage.setText("Sussefully added account");
        }

      }
    });
  }
  
  private boolean hasEmptyFields() {
    boolean result = false;
    if(firstName.getText().equals("")) {
      firstNameError.setText("Please enter the first name");
      result = true;
    }
    if(lastName.getText().equals("")) {
      lastNameError.setText("Please enter the last name");
      result = true;
    }
    if(studentNumber.getText().equals("")) {
      studentNumberError.setText("Please enter the student number");
      result = true;
    }
    if(emailAddress.getText().equals("")) {
      emailError.setText("Please enter the email address");
      result = true;
    }
    successfulMessage.setText("");
    return result;
  }
  
  private void resetFields() {
    firstNameError.setText("");
    lastNameError.setText("");
    studentNumberError.setText("");
    emailError.setText("");
    firstName.setText("");
    lastName.setText("");
    studentNumber.setText("");
    emailAddress.setText("");
  }
}
