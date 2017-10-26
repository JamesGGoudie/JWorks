package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseAlterer {
  
  /**
   * Adds a column for a problem set to the database
   * @param problemSetKey The unique ID of the problem set.
   * @param connection The connection to the database file.
   * @return True if the column was added, false otherwise.
   */
  protected static boolean addProblemSetToAttemptsRemaining(int problemSetKey,
      Connection connection) {
    
    String columnName = "PROBLEMSET" + problemSetKey;
    
    String sql = "ALTER TABLE ATTEMPTSREMAINING ADD COLUMN "
        + columnName
        + ";";
    
    boolean result = false;
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      
      preparedStatement.executeUpdate();
      
      result = true;
    } catch (SQLException e) {
      System.out.println("Could not add a problem set column to the attemptsRemaining table.");
      e.printStackTrace();
    }
    
    return result;
  }
  
  /**
   * Removes a problem set's column from the AttemptsRemaining Table
   * @param problemSetKey The unique ID of the problem set.
   * @param connection The connection to the database file.
   * @return True if the column was removed, false if an error occurred.
   */
  protected static boolean removeProblemSetFromAttemptsRemaining(int problemSetKey,
      Connection connection) {
    
    String columnName = "PROBLEMSET" + problemSetKey;
    
    String sql = "ALTER TABLE ATTEMPTSREMAINING DROP COLUMN "
        + columnName
        + ";";
    
    boolean result = false;
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      
      preparedStatement.executeUpdate();
      
      result = true;
    } catch (SQLException e) {
      System.out.println("Could not remove a problem set column from attemptsRemaining.");
      e.printStackTrace();
    }
    
    return result;
  }
}
