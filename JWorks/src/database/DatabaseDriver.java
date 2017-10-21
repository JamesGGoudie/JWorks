package database;

import java.sql.Connection;

import exceptions.ConnectionFailedExcepetion;

public interface DatabaseDriver {
  /**
   * If the database exists, will connect to it. Otherwise, will create the database.
   * @return The connection to the SQLite database.
   */
  static Connection connectOrCreateDatabase() {
    return null;
  }
  
  /**
   * Will add the tables to the database.
   * @param connection The connection to the database which the tables will be written to.
   * @return The connection to the database that was passed in.
   * @throws ConnectionFailedExcepetion Thrown if the tables were not added to the database.
   */
  static Connection initialize(Connection connection) throws ConnectionFailedExcepetion {
    return null;
  }
}
