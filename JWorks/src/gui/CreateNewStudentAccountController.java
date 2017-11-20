package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CreateNewStudentAccountController {
	@FXML
	private TextField firstName;
	@FXML
	private TextField lastName;
	@FXML
	private TextField studentNumber;
	@FXML
	private TextField emailAddress;
	@FXML
	private TextField password;
	@FXML
	private TextField confirmPassword;
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
	@FXML
	private Label passwordError;
	@FXML
	private Label passwordConfirmError;

	private String name;
	private String email;
	private String passwordInput;
	private String studentNumberInput;

	public void start(CreateNewStudentAccountManager createNewStudentAccountManager) {

		submitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (!hasEmptyFields() && passwordMatched()) {
					name = firstName.getText() + " " + lastName.getText();
					email = emailAddress.getText();
					studentNumberInput = studentNumber.getText();
					passwordInput = password.getText();
					createNewStudentAccountManager.createStudentAccount(name, email, passwordInput, studentNumberInput);
					resetFields();
					successfulMessage.setText("Sussefully added account");
				}
			}
		});
	}
	

	private boolean passwordMatched() {
		boolean result = false;
		if (password.getText().equals(confirmPassword.getText())) {
			result = true;
		} else {
			passwordConfirmError.setText("Password and confirmation password did not match");
		}
		return result;
	}


	private boolean hasEmptyFields() {
		boolean result = false;
		if (firstName.getText().equals("")) {
			firstNameError.setText("Please enter the first name");
			result = true;
		} else {
			firstNameError.setText("");
		}
		if (lastName.getText().equals("")) {
			lastNameError.setText("Please enter the last name");
			result = true;
		} else {
			lastNameError.setText("");
		}
		if (studentNumber.getText().equals("")) {
			studentNumberError.setText("Please enter the student number");
			result = true;
		} else {
			studentNumberError.setText("");
		}
		if (emailAddress.getText().equals("")) {
			emailError.setText("Please enter the email address");
			result = true;
		} else {
			emailError.setText("");
		}
		if (password.getText().equals("")) {
			passwordError.setText("Please enter the password");
			result = true;
		} else {
			passwordError.setText("");
		}
		if (confirmPassword.getText().equals("")) {
			if (password.getText().equals("")) {
				passwordConfirmError.setText("Please enter the password");
			} else {
				passwordConfirmError.setText("Please enter the password again");
			}
			result = true;
		} else {
			passwordConfirmError.setText("");
		}
		successfulMessage.setText("");
		return result;
	}

	private void resetFields() {
		firstNameError.setText("");
		lastNameError.setText("");
		studentNumberError.setText("");
		emailError.setText("");
		passwordError.setText("");
		passwordConfirmError.setText("");
		firstName.setText("");
		lastName.setText("");
		studentNumber.setText("");
		emailAddress.setText("");
		password.setText("");
		confirmPassword.setText("");
		;
	}
}
