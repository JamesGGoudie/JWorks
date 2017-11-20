/**
 * 
 */
package action;

import databaseAPI.DatabaseExtractAPI;
import exceptions.DatabaseSelectException;
import models.Student;

import java.sql.SQLException;
import java.text.ParseException;

/**
 * Action responsible for passing login credentials to the API
 *
 */
public class LoginAction extends Action {

	/**
	 * Given a username and password, returning an object determining whether or not the object is successful.
	 * @param params The parameters to pass into this Action.
	 * 	The first parameter is the login username as a String.
	 * 	The second parameter is the login password as a String.
	 * 	The third parameter is the DatabaseExtractAPI object.
	 * @return A Student object if a Student user successfully logged in. Returns false otherwise.
	 */
	@Override
	public Object execute(Object... params) {
		// Parse parameters
		String user = (String) params[0];
		String password = (String) params[1];
		DatabaseExtractAPI api = (DatabaseExtractAPI) params[2];
		
		// Get user object from API
		Student student = new Student();
		try {
			api.actOnDatabase(Integer.parseInt(user), student);
		} catch (DatabaseSelectException | SQLException e) {
			// Authentication failed case
			return false;
		} catch (NumberFormatException e) {
			// Instructor login case
			return true;
		}

		// Compare credentials
		if (student.getPassword().equals(password)) {
			return student;
		}

		// Only reached if no authentication occurs
		return false;
	}

}
