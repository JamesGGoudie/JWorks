package gui;

import io.FileParser;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CreateNewQuestionScreenManager extends Manager {
	private final String COMMAND = "AddSimpleProblemCommand";
	private String[] inputValues;

	/**
	 * Create a new question with the given parameters
	 * 
	 * @param inputValues
	 *            The parameters for the action
	 */
	public void createNewQuestion(String question, String answer, String tagString) {
		inputValues = new String[] { COMMAND, question, answer, tagString };
		// execute the action in interpreter
		final boolean success = interpreter.executeAction(inputValues);

		// inform user of command result
		if (success) {
			interpreter.getOutputGenerator().output("Problem successfully added!");
		} else {
			interpreter.getOutputGenerator().output("Something went wrong!");
		}
	}

	/**
	 * Imports problems from the given file.
	 * @param file the file to import problems from.
	 */
	public void createBulkProblems(File file) {
		try {
			List<String[]> problems = FileParser.generateArgStringFromFile(file, COMMAND);
			for (String[] problemCommands : problems) {
				interpreter.executeAction(problemCommands);
			}
			interpreter.getOutputGenerator().output("Problems successfully imported!");
		} catch (IOException e) {
			interpreter.getOutputGenerator().output("Something went wrong! Check the format of your file");
		}
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
