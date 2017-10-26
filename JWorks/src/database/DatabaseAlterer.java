package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

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
      Statement statement = connection.createStatement();
      statement.executeUpdate(sql);
      statement.close();
      
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
    
    boolean result = false;
    String sql = "ALTER TABLE ATTEMPTSREMAINING RENAME TO ATTEMPTSREMAINING_TEMP";
    
    try {
      Statement statement = connection.createStatement();
      statement.executeUpdate(sql);
      
      sql = "SELECT * FROM ATTEMPTSREMAINING_TEMP";
      statement = connection.createStatement();
      
      ResultSet resultsFull = statement.executeQuery(sql);
      ResultSetMetaData resultsMetaData = resultsFull.getMetaData();
      int numberOfColumns = resultsMetaData.getColumnCount();
      
      String columnToRemove = "PROBLEMSET" + Integer.toString(problemSetKey);
      
      sql = "CREATE TABLE IF NOT EXISTS ATTEMPTSREMAINING "
          + "(STUDENTNUMBER INTEGER PRIMARY KEY NOT NULL,"
          + "FOREIGN KEY(STUDENTNUMBER) REFERENCES STUDENTS(STUDENTNUMBER))";

      statement = connection.createStatement();
      statement.executeUpdate(sql);
      
      for (int columnIndex = 2; columnIndex < (numberOfColumns + 1); columnIndex++) {
        String columnName = resultsMetaData.getColumnName(columnIndex);
        if (columnName != columnToRemove) {
          
          sql = "ALTER TABLE ATTEMPTSREMAINING ADD COLUMN "
              + columnName
              + ";";
          
          statement = connection.createStatement();
          statement.executeUpdate(sql);
        }
      }

      resultsFull.close();
      
      sql = "DROP TABLE ATTEMPTSREMAINING_TEMP";
      
      statement = connection.createStatement();
      statement.executeUpdate(sql);
      statement.close();
      
      result = true;
      
    } catch (SQLException e) {
      System.out.println("Could not remove a problem set column from ATTEMPTSREMAINING.");
      e.printStackTrace();
    }
    return result;
  }
}
