package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface DatabaseDeleter {
  
  /**
   * Removes a problem from the database.
   * @param problemKey The unique key of the problem.
   * @param connection The connection to the database.
   * @return True if the problem was deleted, false otherwise.
   */
  static boolean deleteProblem(int problemKey, Connection connection) {
    String sql = "DELETE FROM PROBLEMS WHERE ID = ?";
    boolean result = false;
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, problemKey);
      preparedStatement.executeUpdate();
      result = true;
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("A problem occurred while attempting to delete a question.");
    }
    
    return result;
  }
}
