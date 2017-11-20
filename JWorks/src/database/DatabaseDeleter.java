package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseDeleter {
  
  /**
   * Removes a problem from the database.
   * @param problemKey The unique key of the problem.
   * @param connection The connection to the database file.
   * @return True if the problem was deleted, false otherwise.
   */
  protected static boolean deleteProblem(int problemKey, Connection connection) {
    String sql = "DELETE FROM PROBLEMS WHERE ID = ?";
    boolean result = false;
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, problemKey);
      preparedStatement.executeUpdate();
      
      preparedStatement.close();
      
      result = true;
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("A problem occurred while attempting to delete a problem.");
    }
    
    return result;
  }
  
  /**
   * Removes a problem set from the database along with the attempts remaining for each student.
   * @param problemSetKey The unique ID of the problem set.
   * @param connection The connection to the database file.
   * @return True if all components of the problem set was deleted, false otherwise.
   */
  protected static boolean deleteProblemSet(int problemSetKey, Connection connection) {
    String sql = "DELETE FROM PROBLEMSETS WHERE ID = ?";
    boolean result = false;
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, problemSetKey);
      preparedStatement.executeUpdate();
      
      preparedStatement.close();
      
      result = true;
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("A problem occurred while attempting to delete a problem set.");
    }
    
    return result;
  }
  
  /**
   * Removes a student from the database.
   * @param studentNumber The unique number associated with the student.
   * @param connection The connection to the database file.
   * @return True if the student was deleted, false otherwise.
   */
  protected static boolean deleteStudent(int studentNumber, Connection connection) {
    String sql = "DELETE FROM STUDENTS WHERE STUDENTNUMBER = ?";
    boolean result = false;
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, studentNumber);
      preparedStatement.executeUpdate();
      
      preparedStatement.close();
      
      result = true;
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("A problem occurred while attempting to delete a student.");
    }
    
    return result;
  }
  
  /**
   * Removes an instructor from the database.
   * @param instructorID The unique ID of the instructor.
   * @param connection The connection to the database file.
   * @return True if the instructor was deleted, false otherwise.
   */
  protected static boolean deleteInstructor(int instructorID, Connection connection) {
    String sql = "DELETE FROM INSTRUCTORS WHERE ID = ?";
    boolean result = false;
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, instructorID);
      preparedStatement.executeUpdate();
      
      preparedStatement.close();
      
      result = true;
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("A problem occurred while attempting to delete an instructor.");
    }
    
    return result;
  }
}
