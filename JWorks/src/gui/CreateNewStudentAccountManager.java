package gui;

import io.FileParser;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
		inputValues = new String[] {COMMAND, name, email, password, studentNumber, ""};
		interpreter.executeAction(inputValues);
	}

	/**
	 * Creates student accounts in bulk by reading the given file line by line and parsing the arguments.
	 * @param file the file to read
	 */
	public void createBulkStudentAccounts(File file) {
		try {
			List<String[]> students = FileParser.generateArgStringFromFile(file, COMMAND);
			for (String[] studentCommand : students) {
				interpreter.executeAction(studentCommand);
			}
			interpreter.getOutputGenerator().output("Students successfully imported!");
		} catch (IOException e) {
			// Show some error
			interpreter.getOutputGenerator().output("Something went wrong! Check the format of your file");
		}
	}
	

}
