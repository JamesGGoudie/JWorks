package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface DatabaseUpdater {

  /**
   * Updates the text body of a problem already in the database.
   * @param newQuestion The new text body for a pre-existing problem.
   * @param problemKey The unique key of the problem.
   * @param connection The connection to the database.
   * @return True if the question was updated, false if otherwise.
   */
  static boolean updateProblemBody(String newQuestion, int problemKey, Connection connection) {
    String sql = "UPDATE PROBLEMS SET QUESTION = ? WHERE ID = ?;";
    boolean result = false;
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, newQuestion);
      preparedStatement.setInt(2, problemKey);
      preparedStatement.executeUpdate();
      result = true;
        
    } catch (SQLException e) {
      System.out.println("A problem occurred while attempting to modify a problems question.");
      e.printStackTrace();
    }
    
    return result;
  }
  
  /**
   * Updates the answer of a problem already in the database.
   * @param newAnswer The new answer for a pre-existing problem.
   * @param problemKey The unique key of the problem.
   * @param connection The connection to the database.
   * @return True if the answer was updated, false if otherwise.
   */
  static boolean updateProblemAnswer(String newAnswer, int problemKey, Connection connection) {
    String sql = "UPDATE PROBLEMS SET ANSWER = ? WHERE ID = ?;";
    boolean result = false;
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, newAnswer);
      preparedStatement.setInt(2, problemKey);
      preparedStatement.executeUpdate();
      result = true;
        
    } catch (SQLException e) {
      System.out.println("A problem occurred while attempting to modify a problems answer.");
      e.printStackTrace();
    }
    
    return result;
  }
}
