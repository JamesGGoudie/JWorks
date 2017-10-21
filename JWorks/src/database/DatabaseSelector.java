package database;

import java.sql.Connection;
import java.sql.ResultSet;

import exceptions.DatabaseSelectException;

public interface DatabaseSelector {

  /**
   * Gets all of the problems from the database.
   * @param connection The connection to the database.
   * @return A ResultSet containing all of the problems from the database.
   * @throws DatabaseSelectException Thrown if the problems could not be retrieved from the
   *                                 database. 
   */
  static ResultSet getAllProblems(Connection connection) throws DatabaseSelectException {
    return null;
  }
  
  /**
   * Gets a problem from the database.
   * @param problemKey The unique key of the question in the database.
   * @param connection The connection to the database.
   * @return A ResultSet containing data from one question.
   * @throws DatabaseSelectException Thrown if the question could not be retrieved from the
   *                                 database.
   */
  static ResultSet getSingleProblem(int problemKey, Connection connection)
      throws DatabaseSelectException {
    return null;
  }
}
