package gui;

import javafx.scene.layout.Pane;

public class CreateNewStudentAccountManager extends Manager {
	private CreateNewStudentAccountController controller;
	private final String COMMAND = "AddStudentCommand";
	private String[] inputValues;

	public void showScreen(Pane innerPane) {
		// load the new inner pane from fxml file
		loader = loadNewPane(loader, innerPane, "CreateNewStudentAccount.fxml");
		// Load the controller and start the controller
		controller = loader.<CreateNewStudentAccountController>getController();
		controller.start(this);
	}

	public void createStudentAccount(String name, String email, String password, String studentNumber) {
		inputValues = new String[] {COMMAND, name, email, password, studentNumber};
		interpreter.executeAction(inputValues);
	}
	

}
