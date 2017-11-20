package gui;

import javafx.scene.layout.Pane;

public class CreateNewQuestionScreenManager extends Manager {
	private final String COMMAND = "AddSimpleProblemCommand";
	private String[] inputValues;

	/**
	 * Create a new question with the given parameters
	 * 
	 * @param inputValues
	 *            The parameters for the action
	 */
	public void createNewQuestion(String question, String answer) {
		inputValues = new String[] { COMMAND, question, answer };
		// execute the action in interpreter
		interpreter.executeAction(inputValues);
	}

	/**
	 * Load and display the create new question screen
	 * 
	 * @param innerPane
	 *            The pane that new contents are loading into
	 */
	public void showScreen(Pane innerPane) {
		loader = loadNewPane(loader, innerPane, "CreateNewQuestionScreen.fxml");
		CreateNewQuestionScreenController controller = loader.<CreateNewQuestionScreenController>getController();
		controller.start(this);
	}
}
