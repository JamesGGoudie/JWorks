package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import exceptions.ConnectionFailedException;

public interface DatabaseDriver {
  /**
   * If the database exists, will connect to it. Otherwise, will create the database.
   * @return The connection to the SQLite database.
   */
  static Connection connectOrCreateDatabase() {
    Connection connection = null;
    
    try {
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection("jdbc:sqlite:jworks.db");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      System.out.println("The required SQLite class could not be found.");
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("The connection to the database could not be instantiated.");
    } 
    
    return connection;
  }
  
  /**
   * Will add the tables to the database. Do not call this function from the interface!
   * @param connection The connection to the database which the tables will be written to.
   * @return The connection to the database that was passed in.
   * @throws ConnectionFailedException Thrown if the tables were not added to the database.
   */
  static Connection initialize(Connection connection) throws ConnectionFailedException {
    return null;
  }
}
