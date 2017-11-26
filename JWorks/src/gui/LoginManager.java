package gui;

import javafx.scene.Scene;
import models.Instructor;
import models.User;

public class LoginManager extends Manager {
	private Scene scene;
	private LoginController controller;
	private InstructorMainScreenManager instructorMainScreenManager;
	private StudentMainScreenManager studentMainScreenManager;
	private final String COMMAND = "LoginCommand";
	private final String ADMIN = "admin";

	/**
	 * Default constructor
	 * 
	 * @param scene
	 *            The scene of the screen
	 */
	public LoginManager(Scene scene) {
		this.scene = scene;
	}

	/**
	 * Load the corresponding instructor/student screen based on the user's input
	 * 
	 * @param user
	 *            The name of the user
	 */

	public boolean Login(String user, String password) {
		String[] args = { COMMAND, user, password };
		if (interpreter.executeAction(args)) {
			// Auth is successful, store user appropriately
			try {
				User sessionUser = (User) interpreter.getOutputGenerator().getLastResult();
				interpreter.setCurrentUser(sessionUser);
			} catch (ClassCastException e) {
				// Instructor login case
				interpreter.setCurrentUser(new Instructor(user, "", "", 1));
			}

			if (user.matches("[0-9]+")) {
				showStudentMainScreen(user);
			} else {
				if (user.equals(ADMIN) && password.equals(ADMIN)) {
					ShowInstructorMainScreen(user);
				}else {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * Return the user to the login screen
	 */
	public void logout() {
		showScreen();
	}

	/**
	 * Display the login screen
	 */
	public void showScreen() {
		// load the fxml file that contains the layout of the login screen
		loader = loadNewScreen(loader, scene, "LoginScreen.fxml");
		// get the controller
		controller = loader.<LoginController>getController();
		// start the controller
		controller.start(this);
	}

	/**
	 * Set the window to show the instructor main screen
	 * 
	 * @param user
	 *            The name of the current user
	 */
	private void ShowInstructorMainScreen(String user) {
		// create a new instance of instructor main screen manager
		instructorMainScreenManager = new InstructorMainScreenManager(scene);
		// show the main screen
		instructorMainScreenManager.showScreen(this, user);
	}

	/**
	 * Set the window to show the student main screen
	 * 
	 * @param user
	 */
	private void showStudentMainScreen(String user) {
		studentMainScreenManager = new StudentMainScreenManager(scene);
		studentMainScreenManager.showScreen(this, user);
	}

}