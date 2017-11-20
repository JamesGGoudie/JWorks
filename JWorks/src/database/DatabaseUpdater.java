package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class DatabaseUpdater {

  /**
   * Updates the text body of a problem already in the database.
   * @param newQuestion The new text body for a pre-existing problem.
   * @param problemKey The unique key of the problem.
   * @param connection The connection to the database.
   * @return True if the question was updated, false if otherwise.
   */
  protected static boolean updateProblemBody(String newQuestion, int problemKey, Connection connection) {
    String sql = "UPDATE PROBLEMS SET QUESTION = ? WHERE ID = ?;";
    boolean result = false;
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, newQuestion);
      preparedStatement.setInt(2, problemKey);
      
      preparedStatement.executeUpdate();
      
      preparedStatement.close();
      
      result = true;
    } catch (SQLException e) {
      System.out.println("A problem occurred while attempting to modify a problems question.");
      e.printStackTrace();
    }
    
    return result;
  }
  
  /**
   * Updates the answer of a problem already in the database.
   * @param newAnswer The new answer for a pre-existing problem.
   * @param problemKey The unique key of the problem.
   * @param connection The connection to the database.
   * @return True if the answer was updated, false if otherwise.
   */
  protected static boolean updateProblemAnswer(String newAnswer, int problemKey, Connection connection) {
    String sql = "UPDATE PROBLEMS SET ANSWER = ? WHERE ID = ?;";
    boolean result = false;
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, newAnswer);
      preparedStatement.setInt(2, problemKey);
      
      preparedStatement.executeUpdate();
      
      preparedStatement.close();
      
      result = true;
    } catch (SQLException e) {
      System.out.println("A problem occurred while attempting to modify a problems answer.");
      e.printStackTrace();
    }
    
    return result;
  }
  
  /**
   * Changes the specified student's name to the one given.
   * @param studentNumber The unique ID of the student.
   * @param name The new name of the student.
   * @param connection The connection to the database file.
   * @return True if the name was changed, false otherwise.
   */
  protected static boolean updateStudentName(int studentNumber, String name, Connection connection) {
    String sql = "UPDATE STUDENTS SET NAME = ? WHERE STUDENTNUMBER = ?;";
    boolean result = false;
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, name);
      preparedStatement.setInt(2, studentNumber);
      
      preparedStatement.executeUpdate();
      
      preparedStatement.close();
      
      result = true;
    } catch (SQLException e) {
      System.out.println("A problem occurred while attempting to change a students name.");
      e.printStackTrace();
    }
    
    return result;
  }
  
  /**
   * Changes the specified student's email to the one given.
   * @param studentNumber The unique ID of the student.
   * @param email The new email of the student.
   * @param connection The connection to the database file.
   * @return True if the email was changed, false otherwise.
   */
  protected static boolean updateStudentEmail(int studentNumber, String email,
      Connection connection) {
    String sql = "UPDATE STUDENTS SET EMAIL = ? WHERE STUDENTNUMBER = ?;";
    boolean result = false;
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, email);
      preparedStatement.setInt(2, studentNumber);
      
      preparedStatement.executeUpdate();
      
      preparedStatement.close();
      
      result = true;
    } catch (SQLException e) {
      System.out.println("A problem occurred while attempting to change a students email.");
      e.printStackTrace();
    }
    
    return result;
  }
  
  /**
   * Changes the max amount of attempts allowed for the given problem set.
   * @param problemSetKey The unique ID of the problem set.
   * @param maxAttempts The maximum amount of allowed attempts.
   * @param connection The connection to the database file.
   * @return True if the attempts cap was changed, false otherwise.
   */
  protected static boolean updateProblemSetMaxAttempts(int problemSetKey, int maxAttempts,
      Connection connection) {
    String sql = "UPDATE PROBLEMSETS SET MAXATTEMPTS = ? WHERE ID = ?;";
    boolean result = false;
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, maxAttempts);
      preparedStatement.setInt(2, problemSetKey);
      
      preparedStatement.executeUpdate();
      
      preparedStatement.close();
      
      result = true;
    } catch (SQLException e) {
      System.out.println("A problem occurred while updating the max amount of attempts.");
      e.printStackTrace();
    }
    
    return result;
  }

  /**
   * Replaces the problems in the desired problem set with those given.
   * @param problemSetKey The unique ID of the problem set.
   * @param newProblems The unique IDs of the problems.
   * @param connection The connection to the database file.
   * @return True if the problem set was modified, false otherwise.
   */
  protected static boolean updateProblemSetProblems(int problemSetKey, int[] newProblems,
      Connection connection) {
    
    String problemsFormatted = "";
    
    for (int problemID : newProblems) {
      problemsFormatted = Integer.toString(problemID) + ";";
    }
    
    String sql = "UPDATE PROBLEMSETS SET PROBLEMS = ? WHERE ID = ?;";
    boolean result = false;
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, problemsFormatted);
      preparedStatement.setInt(2, problemSetKey);
      
      preparedStatement.executeUpdate();
      
      preparedStatement.close();
      
      result = true;
    } catch (SQLException e) {
      System.out.println("A problem occurred while updating the problems in the set.");
      e.printStackTrace();
    }
    
    return result;
  }
  
  /**
   * Changes the amount of remaining attempts available to the given student for the given problem
   * set.
   * @param problemSetKey The unique ID of the problem set.
   * @param studentNumber The unique number of the student.
   * @param attemptsRemaining The new amount of remaining attempts.
   * @param connection The connection to the database file.
   * @return True if the remaining attempts was modified, false otherwise.
   */
  protected static boolean updateAttemptsRemaining(int problemSetKey, int studentNumber,
      int attemptsRemaining, Connection connection) {
    
    String sql = "UPDATE ATTEMPTSREMAINING SET ATTEMPTSREMAINING = ? WHERE STUDENTNUMBER = ?, "
        + "PROBLEMSET = ?";
    
    boolean result = false;
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, attemptsRemaining);
      preparedStatement.setInt(2, studentNumber);
      preparedStatement.setInt(3, problemSetKey);
      
      preparedStatement.executeUpdate();
      
      preparedStatement.close();
      
      result = true;
    } catch (SQLException e) {
      System.out.println("A problem occurred while updating the problems in the set.");
      e.printStackTrace();
    }
    
    return result;
  }
  
  /**
   * Changes the starting time of the problem set in the database to the new one given.
   * @param problemSetKey The unique ID of the problem set.
   * @param newStartTime The new release time of the problem set.
   * @param connection The connection to the database file.
   * @return True if successful, false otherwise.
   */
  protected static boolean updateStartTime(int problemSetKey, Date newStartTime,
      Connection connection) {
   
   String sql = "UPDATE PROBLEMSETS SET STARTTIME = ? WHERE ID = ?;";
   boolean result = false;
   long timeSinceEpoch = newStartTime.getTime();
   
   try {
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setLong(1, timeSinceEpoch / 1000L);
    preparedStatement.setInt(2, problemSetKey);
    preparedStatement.executeUpdate();
    preparedStatement.close();
    
    result = true;
  } catch (SQLException e) {
    System.out.println("A problem occurred while updating the release time.");
    e.printStackTrace();
  }
   
   return result;
  }
  
  /**
   * Changes the starting time of the problem set in the database to the new one given.
   * @param problemSetKey The unique ID of the problem set.
   * @param newStartTime The new release time of the problem set.
   * @param connection The connection to the database file.
   * @return True if successful, false otherwise.
   */
  protected static boolean updateEndTime(int problemSetKey, Date newEndTime,
      Connection connection) {
   
   String sql = "UPDATE PROBLEMSETS SET ENDTIME = ? WHERE ID = ?;";
   boolean result = false;
   long timeSinceEpoch = newEndTime.getTime();
   
   try {
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setLong(1, timeSinceEpoch / 1000L);
    preparedStatement.setInt(2, problemSetKey);
    preparedStatement.executeUpdate();
    preparedStatement.close();
    
    result = true;
  } catch (SQLException e) {
    System.out.println("A problem occurred while updating the due date.");
    e.printStackTrace();
  }
   
   return result;
  }
}
