package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import exceptions.DatabaseSelectException;

public class DatabaseSelector {

  /**
   * Gets all of the problems from the database.
   * @param connection The connection to the database.
   * @return A ResultSet containing all of the problems from the database, null is there was an
   *         uncaught error.
   * @throws DatabaseSelectException Thrown if the problems could not be retrieved from the
   *                                 database. 
   */
  protected static ResultSet getAllProblems(Connection connection) throws DatabaseSelectException {
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
   * @return A ResultSet containing data from one question, null is there was an uncaught error.
   * @throws DatabaseSelectException Thrown if the question could not be retrieved from the
   *                                 database.
   */
  protected static ResultSet getSingleProblem(int problemKey, Connection connection)
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
  
  /**
   * Returns a student from the database.
   * @param studentNumber The unique number associated with the student.
   * @param connection The connection to the database file.
   * @return A ResultSet containing data about the student, null if there was a uncaught error.
   * @throws DatabaseSelectException Thrown if the student could not be retrieved from the
   *                                 database.
   */
  protected static ResultSet getStudent(int studentNumber, Connection connection)
      throws DatabaseSelectException {
    
    String sql = "SELECT * FROM STUDENTS WHERE STUDENTNUMBER = ?";
    ResultSet results = null;
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, studentNumber);
      results = preparedStatement.executeQuery();
    } catch (SQLException e) {
      String errorMessage = "Failed to get the student from the database.";
      throw new DatabaseSelectException(errorMessage);
    }
    
    return results;
  }
  
  /**
   * Returns the instructor from the database with the corresponding ID.
   * @param instuctorID The unique ID of the instructor.
   * @param connection The connection to the database file.
   * @return A ResultSet containing data about the instructor, null indicates an error occurred.
   * @throws DatabaseSelectException Thrown if the instructor could not be retrieved from the
   *                                 database.
   */
  protected static ResultSet getInstructor(int instuctorID, Connection connection)
      throws DatabaseSelectException {
    
    ResultSet results = null;
    String sql = "SELECT * FROM INSTRUCTORS WHERE ID = ?";
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, instuctorID);
      results = preparedStatement.executeQuery();
    } catch (SQLException e) {
      String errorMessage = "Failed to get the instructor from the database.";
      throw new DatabaseSelectException(errorMessage);
    }
    
    return results;
  }
  
  /**
   * Returns a problem set from the database.
   * @param problemSetKey The unique ID of the problem set.
   * @param connection The connection to the database file.
   * @return A ResultSet containing data about a problem set, null if there was a uncaught error.
   * @throws DatabaseSelectException Thrown if the problem set could not be retrieved from the
   *                                 database.
   */
  protected static ResultSet getProblemSet(int problemSetKey, Connection connection)
      throws DatabaseSelectException {
    
    String sql = "SELECT * FROM PROBLEMSETS WHERE ID = ?";
    ResultSet results = null;
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, problemSetKey);
      results = preparedStatement.executeQuery();
    } catch (SQLException e) {
      String errorMessage = "Failed to get the problem set from the database.";
      throw new DatabaseSelectException(errorMessage);
    }
    
    return results;
  }
  
  /**
   * Retrieves data on the problems associated with a given problem set.
   * @param problemSetKey The unique ID of the problem set.
   * @param connection The connection to the database file.
   * @return A ResultSet of the problems-problemSet relationship table containing the problem IDs
   *         of the problem set, null indicates an error occurred.
   * @throws DatabaseSelectException Thrown if the problems could not be retrieved from the
   *                                 database.
   */
  protected static ResultSet getProblemsInProblemSet(int problemSetKey, Connection connection)
      throws DatabaseSelectException {
    ResultSet results = null;
    String sql = "SELECT * FROM PROBLEMSETS_PROBLEMS_RELATIONSHIP WHERE PROBLEMSET = ?";
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, problemSetKey);
      results = preparedStatement.executeQuery();
    } catch (SQLException e) {
      String errorMessage = "Failed to get the problems in the problem set.";
      throw new DatabaseSelectException(errorMessage);
    }
    
    return results;
  }
  
  /**
   * Retrieves data on all of the problem sets which contain the given problem.
   * @param problemKey The unique key of the problem.
   * @param connection The connection to the database file.
   * @return A ResultSet of the problems-problemSet relationship table containing the problem IDs
   *         of the problem set, null indicated an error occurred.
   * @throws DatabaseSelectException Thrown if the problem sets could not be retrieved from the
   *                                 database.
   */
  protected static ResultSet getProblemSetsWhichContainProblem(int problemKey,
      Connection connection) throws DatabaseSelectException {
    
    ResultSet results = null;
    String sql = "SELECT * FROM PROBLEMSETS_PROBLEMS_RELATIONSHIP WHERE PROBLEM = ?";
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, problemKey);
      results = preparedStatement.executeQuery();
    } catch (SQLException e) {
      String errorMessage = "Failed to get the problem sets which contain the problem.";
      throw new DatabaseSelectException(errorMessage);
    }
    
    
    return results;
  }
  
  /**
   * Returns the amount of attempts that a given student has for a given problem set.
   * @param studentNumber The unique number of the student.
   * @param problemSetKey The unique key of the problem set.
   * @param connection The connection to the database file.
   * @return The attempts remaining, -1 if an uncaught error occurs. -8 implies infinite.
   * @throws DatabaseSelectException Thrown if the remaining attempts could not be retrieved from
   *                                 the database.
   */
  protected static int getAttemptsRemaining(int studentNumber, int problemSetKey,
      Connection connection) throws DatabaseSelectException {
    
    String sql = "SELECT * FROM ATTEMPTSREMAINING WHERE STUDENTNUMBER = ?";
    int result = -1;
    ResultSet data = null;
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, studentNumber);
      data = preparedStatement.executeQuery();
      
      String columnName = "PROBLEMSET" + problemSetKey;
      
      result = data.getInt(columnName);
      
      data.close();
    } catch (SQLException e) {
      String errorMessage = "Failed to get the remaining amount of attempts from the database.";
      throw new DatabaseSelectException(errorMessage);
    }
    
    return result;
  }
  
  /**
   * Gets the time that the problem set is released from the database.
   * @param problemSetKey The unique key of the problem set.
   * @param connection The connection to the database file.
   * @return The problem sets release date if successful, or the epoch if otherwise.
   * @throws DatabaseSelectException Thrown if the release time could not be retrieved.
   */
  protected static Date getStartTime(int problemSetKey, Connection connection)
      throws DatabaseSelectException {
    
    String sql = "SELECT STARTTIME FROM PROBLEMSETS WHERE ID = ?";
    Date result = new Date(0);
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, problemSetKey);
      ResultSet data = preparedStatement.executeQuery();
      
      long timeSinceEpoch = data.getInt(1);
      result.setTime(timeSinceEpoch * 1000L);
      
      data.close();
      
    } catch (SQLException e) {
      e.printStackTrace();
      String errorMessage = "Failed to get the problem sets release time from the database.";
      throw new DatabaseSelectException(errorMessage);
    }
    
    return result;
  }
  
  /**
   * Gets the time that the problem set is due.
   * @param problemSetKey The unique key of the problem set.
   * @param connection The connection to the database file.
   * @return The problem sets due date if successful, or the epoch if otherwise.
   * @throws DatabaseSelectException Thrown if the due date could not be retrieved.
   */
  protected static Date getEndTime(int problemSetKey, Connection connection)
      throws DatabaseSelectException {
    
    String sql = "SELECT ENDTIME FROM PROBLEMSETS WHERE ID = ?";
    Date result = new Date(0);
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, problemSetKey);
      ResultSet data = preparedStatement.executeQuery();
      
      long timeSinceEpoch = data.getInt(1);
      result.setTime(timeSinceEpoch * 1000L);
      
      data.close();
      
    } catch (SQLException e) {
      e.printStackTrace();
      String errorMessage = "Failed to get the problem sets due date from the database.";
      throw new DatabaseSelectException(errorMessage);
    }
    
    return result;
  }
}
