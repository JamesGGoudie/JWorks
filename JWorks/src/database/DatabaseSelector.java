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
   * Returns a problem set from the database. Note that the times need to be multiplied by 1000 to
   * be used as a Date object.
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
    String sql = "SELECT PROBLEM FROM PROBLEMSETS_PROBLEMS_RELATIONSHIP WHERE PROBLEMSET = ?";
    
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
    String sql = "SELECT PROBLEMSET FROM PROBLEMSETS_PROBLEMS_RELATIONSHIP WHERE PROBLEM = ?";
    
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
   * Retrieves data on all of the problems created by an instructor.
   * @param instructorID The unique ID of the instructor.
   * @param connection The connection to the database file.
   * @return A ResultSet of the instructor-problems relationship table containing the problem IDs
   *         of the problems created by the instructor; null indicates that an error occurred.
   * @throws DatabaseSelectException Throw if the instructors problems could not be retrieved from
   *                                 the database.
   */
  protected static ResultSet getInstructorsProblems(int instructorID, Connection connection)
      throws DatabaseSelectException {
    
    ResultSet results = null;
    String sql = "SELECT PROBLEM FROM INSTRUCTORS_PROBLEMS_RELATIONSHIP WHERE INSTRUCTOR = ?";
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, instructorID);
      results = preparedStatement.executeQuery();
    } catch (SQLException e) {
      String errorMessage = "Failed to get the problems created by the instructor.";
      throw new DatabaseSelectException(errorMessage);
    }
    
    return results;
  }
  
  /**
   * Returns the ID of the instructor which created the given problem.
   * @param problemKey The unique ID of the problem.
   * @param connection The connection to the database file.
   * @return The unique ID of the instructor who created the problem; -1 indicates an error
   *         occurred.
   * @throws DatabaseSelectException Thrown if the instructor who created the problem could not be
   *                                 retrieved from the database.
   */
  protected static int getProblemCreator(int problemKey, Connection connection)
      throws DatabaseSelectException {
    
    int result = -1;
    String sql = "SELECT INSTRUCTOR FROM INSTRUCTORS_PROBLEMS_RELATIONSHIP WHERE PROBLEM = ?";
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, problemKey);
      ResultSet data = preparedStatement.executeQuery();
      
      result = data.getInt(1);
    } catch (SQLException e) {
      String errorMessage = "Failed to get the instructor who created the problem.";
      throw new DatabaseSelectException(errorMessage);
    }
    
    return result;
  }
  
  /**
   * Retrieves data on all of the problem sets created by an instructor.
   * @param instructorID The unique ID of the instructor.
   * @param connection The connection to the database file.
   * @return A ResultSet of the instructor-problemSets relationship table containing the problem
   *         set keys of the problem sets created by the instructor; null indicated that an error
   *         occurred.
   * @throws DatabaseSelectException Thrown if the instructors problem sets could not be retrieved
   *                                 from the database.
   */
  protected static ResultSet getInstructorsProblemSets(int instructorID, Connection connection)
      throws DatabaseSelectException {
    
    ResultSet results = null;
    String sql = "SELECT PROBLEMSET FROM INSTRUCTORS_PROBLEMSETS_RELATIONSHIP WHERE INSTRUCTOR = ?";
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, instructorID);
      results = preparedStatement.executeQuery();
    } catch (SQLException e) {
      String errorMessage = "Failed to get the problems sets created by the instructor.";
      throw new DatabaseSelectException(errorMessage);
    }
    
    return results;
  }
  
  /**
   * Returns the ID of the instructor who created the given problem set.
   * @param problemSetKey The unique ID of the problem set.
   * @param connection The connection to the database file.
   * @return The unique ID of the instructor who created the problem set; -1 indicates that an
   *         error occurred.
   * @throws DatabaseSelectException Thrown if the instructor who created the database could not be
   *         retrieved from the database.
   */
  protected static int getProblemSetCreator(int problemSetKey, Connection connection)
      throws DatabaseSelectException {
  
    int result = -1;
    String sql = "SELECT INSTRUCTOR FROM INSTRUCTORS_PROBLEMSETS_RELATIONSHIP WHERE "
        + "PROBLEMSET = ?";
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, problemSetKey);
      ResultSet data = preparedStatement.executeQuery();
      
      result = data.getInt(1);
    } catch (SQLException e) {
      String errorMessage = "Failed to get the instructor who created the problem set.";
      throw new DatabaseSelectException(errorMessage);
    }
    
    return result;
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
    
    String sql = "SELECT ATTEMPTSREMAINING FROM ATTEMPTSREMAINING WHERE (STUDENTNUMBER, PROBLEMSET)"
        + " = (?,?)";
    int result = -1;
    ResultSet data = null;
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, studentNumber);
      preparedStatement.setInt(2, problemSetKey);
      data = preparedStatement.executeQuery();
      
      result = data.getInt(1);
      
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
      String errorMessage = "Failed to get the problem sets due date from the database.";
      throw new DatabaseSelectException(errorMessage);
    }
    
    return result;
  }
  
  /**
   * Returns a result set containing the answers submitted by the student for a problem set.
   * @param studentNumber The unique ID of the student.
   * @param problemSet The unique ID of the problem set.
   * @param attemptNumber The attempt number that we want to access.
   * @param connection The connection to the database file.
   * @return A result set containing a problem ID correlating to a student's answer.
   * @throws DatabaseSelectException Thrown if the previous attempt could not be retrieved.
   */
  protected static ResultSet getStudentsResults(int studentNumber, int problemSet,
      int attemptNumber, Connection connection) throws DatabaseSelectException {
    
    ResultSet results = null;
    String sql = "SELECT PROBLEM, STUDENTANSWER FROM PREVIOUSATTEMPTS WHERE (STUDENTNUMBER,"
        + "PROBLEMSET, ATTEMPTNUMBER) = (?,?,?)";
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      
      preparedStatement.setInt(1, studentNumber);
      preparedStatement.setInt(2, problemSet);
      preparedStatement.setInt(3, attemptNumber);
      
      results = preparedStatement.executeQuery();
    } catch (SQLException e) {
      String errorMessage = "Could not retrieve student's results from the database.";
      throw new DatabaseSelectException(errorMessage);
    }
    
    return results;
  }

  /**
   * Returns a result set containing all of the tags associated with the given problem.
   * @param problemID The unique ID of the problem.
   * @param connection The connection to the database file.
   * @return A result set containing one column, consisting of the problems tags as Strings.
   * @throws DatabaseSelectException Thrown if the tags could not be retrieved.
   */
  protected static ResultSet getProblemsTags(int problemID, Connection connection) throws
      DatabaseSelectException {
    
    ResultSet results = null;
    String sql = "SELECT TAG FROM PROBLEMTAGS WHERE PROBLEM = ?";
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      
      preparedStatement.setInt(1, problemID);
      
      results = preparedStatement.executeQuery();
    } catch (SQLException e) {
      String errorMessage = "Could not retrieve problem's tags from the database.";
      throw new DatabaseSelectException(errorMessage);
    }
    
    
    return results;
  }
  
  /**
   * Returns all of the tags associate with the given problem set.
   * @param problemSetID The unique ID of the problem set.
   * @param connection The connection to the database file.
   * @return A results set containing one column composed of the tags as Strings.
   * @throws DatabaseSelectException Thrown if the tags could not be retrieved from the database.
   */
  protected static ResultSet getProblemSetTags(int problemSetID, Connection connection) throws
      DatabaseSelectException {
    
    ResultSet results = null;
    String sql = "SELECT TAG FROM PROBLEMSETTAGS WHERE PROBLEMSET = ?";
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      
      preparedStatement.setInt(1, problemSetID);
      
      results = preparedStatement.executeQuery();
    } catch (SQLException e) {
      String errorMessage = "Could not retrieve problem set's tags from the database.";
      throw new DatabaseSelectException(errorMessage);
    }
    
    
    return results;
  }

  /**
   * Returns the ID's of the problems with the given tag as a result set.
   * @param tag The tag that we want to use to find problems.
   * @param connection The connection to the database file.
   * @return A result set containing one column of integers where the integers are the problem IDs.
   * @throws DatabaseSelectException Thrown if the problems with the given tag could not be
   *                                 retrieved from the database.
   */
  protected static ResultSet getProblemsWithTag(String tag, Connection connection) throws
      DatabaseSelectException {
    
    ResultSet results = null;
    String sql = "SELECT PROBLEM FROM PROBLEMTAGS WHERE TAG = ?";
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      
      preparedStatement.setString(1, tag);
      
      results = preparedStatement.executeQuery();
    } catch (SQLException e) {
      String errorMessage = "Could not retrieve the problem with the given tag.";
      throw new DatabaseSelectException(errorMessage);
    }
    
    return results;
  }

  /**
   * Returns the ID's of the problem sets with the given tag as a result set.
   * @param tag The tag that we want to use to find problem sets.
   * @param connection The connection to the database file.
   * @return A result set containing one column of integers where the integers are the problem set
   *         IDs.
   * @throws DatabaseSelectException Thrown if the problem set's with the given tag could not be
   *                                 retrieved from the database.
   */
  protected static ResultSet getProblemSetsWithTag(String tag, Connection connection) throws
      DatabaseSelectException {
    
    ResultSet results = null;
    String sql = "SELECT PROBLEMSET FROM PROBLEMSETTAGS WHERE TAG = ?";
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      
      preparedStatement.setString(1, tag);
      
      results = preparedStatement.executeQuery();
    } catch (SQLException e) {
      String errorMessage = "Could not retrieve the problem sets with the given tag.";
      throw new DatabaseSelectException(errorMessage);
    }
    
    return results;
  }
  
  /**
   * Returns a result set containing all of the tags and the problems that they are associated
   * with.
   * @param connection The connection to the database file.
   * @return A result set containing two columns: first, a problemID; second, a tag associated
   *         with the problem.
   * @throws DatabaseSelectException Thrown if the collection could not be retrieved.
   */
  protected static ResultSet getAllProblemTags(Connection connection)
      throws DatabaseSelectException {
    
    ResultSet results = null;
    String sql = "SELECT * FROM PROBLEMTAGS";
    
    try {
      Statement statement = connection.createStatement();
      results = statement.executeQuery(sql);
    } catch (SQLException e) {
      String errorMessage = "Failed to get the collection of problems and tags from the database.";
      throw new DatabaseSelectException(errorMessage);
    }
    
    return results;
  }
  
  /**
   * Returns a result set containing all of the tags and the problems sets that they are associated
   * with.
   * @param connection The connection to the database file.
   * @return A result set containing two columns: first, a problemSetID; second, a tag associated
   *         with the problem set.
   * @throws DatabaseSelectException Thrown if the collection could not be retrieved.
   */
  protected static ResultSet getAllProblemSetTags(Connection connection)
      throws DatabaseSelectException {
    
    ResultSet results = null;
    String sql = "SELECT * FROM PROBLEMSETTAGS";
    
    try {
      Statement statement = connection.createStatement();
      results = statement.executeQuery(sql);
    } catch (SQLException e) {
      String errorMessage = "Failed to get the collection of problems sets and tags.";
      throw new DatabaseSelectException(errorMessage);
    }
    
    return results;
  }
}
