package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseDeleter {
  
  /**
   * Removes a problem from the database.
   * @param problemKey The unique key of the problem.
   * @param connection The connection to the database file.
   * @return True if the problem was deleted, false otherwise.
   */
  protected static boolean deleteProblem(int problemKey, Connection connection) {
    String sql = "DELETE FROM PROBLEMS WHERE ID = ?";
    boolean result = false;
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, problemKey);
      preparedStatement.executeUpdate();
      result = true;
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("A problem occurred while attempting to delete a problem.");
    }
    
    return result;
  }
  
  /**
   * Removes a problem set from the database along with the attempts remaining for each student.
   * @param problemSetKey The unique ID of the problem set.
   * @param connection The connection to the database file.
   * @return True if all components of the problem set was deleted, false otherwise.
   */
  protected static boolean deleteProblemSet(int problemSetKey, Connection connection) {
    String sql = "DELETE FROM PROBLEMSETS WHERE ID = ?";
    boolean result = false;
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, problemSetKey);
      preparedStatement.executeUpdate();
      
      result = DatabaseAlterer.removeProblemSetFromAttemptsRemaining(problemSetKey, connection);
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("A problem occurred while attempting to delete a problem set.");
    }
    
    return result;
  }
}
