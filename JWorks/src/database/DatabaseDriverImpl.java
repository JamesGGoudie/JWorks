package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import exceptions.ConnectionFailedException;

public class DatabaseDriverImpl implements DatabaseDriver {
  /**
   * Will add the tables to the database.
   * @param connection The connection to the database which the tables will be written to.
   * @return The connection to the database that was passed in.
   * @throws ConnectionFailedException Thrown if the tables were not added to the database.
   */
  static Connection initialize(Connection connection) throws ConnectionFailedException {
    if (!initializeDatabase(connection)) {
      throw new ConnectionFailedException();
    }
    
    return connection;
  }
  
  /**
   * Creates all of the tables to be used in the database.
   * @param connection The connection to the database file.
   * @return True if all tables were created; false otherwise.
   */
  private static boolean initializeDatabase(Connection connection) {
    Statement statement = null;
    boolean result = false;
    
    try {
      statement = connection.createStatement();
      
      String sql = "CREATE TABLE IF NOT EXISTS PROBLEMS " 
          + "(ID INTEGER PRIMARY KEY NOT NULL," 
          + "TYPE INTEGER NOT NULL,"
          + "QUESTION TEXT NOT NULL,"
          + "ANSWER NOT NULL)";
      statement.executeUpdate(sql);
      
      statement.close();
      result = true;
      
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("A problem occurred while trying to create the tables.");
    }
    
    return result;
  }
}
