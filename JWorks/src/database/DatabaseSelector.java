package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    String sql = "SELECT * FROM PROBLEMS";
    ResultSet results = null;
    
    try {
      Statement statement = connection.createStatement();
      results = statement.executeQuery(sql);
    } catch (SQLException e) {
      e.printStackTrace();
      String errorMessage = "Failed to get entire problem collection from database.";
      throw new DatabaseSelectException(errorMessage);
    }
    
    return results;
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
    String sql = "SELECT * FROM PROBLEMS WHERE ID = ?";
    ResultSet results = null;
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, problemKey);
      results = preparedStatement.executeQuery();
    } catch (SQLException e) {
      e.printStackTrace();
      String errorMessage = "Failed to get the problem from database.";
      throw new DatabaseSelectException(errorMessage);
    }
    
    return results;
  }
}
