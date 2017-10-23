package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import exceptions.DatabaseInsertException;

public class DatabaseInserter {

  /**
   * Inserts a problem into the database.
   * @param type An integer used to represent the questions type.
   * @param question The text that would be prompted to the student.
   * @param answer The answer to the question.
   * @param connection The connection to the database.
   * @return The unique key of the problem.
   * @throws DatabaseInsertException Thrown if the question could not be added to the database.
   */
  protected static int insertProblem(int type, String question, String answer, Connection connection)
      throws DatabaseInsertException {
    String sql = "INSERT INTO PROBLEMS(TYPE, QUESTION, ANSWER) VALUES(?,?,?)";
    int result = -1;
    
      PreparedStatement preparedStatement = null;
    try {
        preparedStatement = connection.prepareStatement(sql, 
              Statement.RETURN_GENERATED_KEYS);
    } catch (SQLException e) {
        e.printStackTrace();

        // String errorMessage = "Failed to insert a problem into the database.";
        // throw new DatabaseInsertException(errorMessage);
    }
      try {
        preparedStatement.setInt(1, type);
    } catch (SQLException e) {
        e.printStackTrace();
    }
      try {
        preparedStatement.setString(2, question);
    } catch (SQLException e) {
        e.printStackTrace();
    }
      try {
        preparedStatement.setString(3, answer);
    } catch (SQLException e) {
        e.printStackTrace();
    }
      
      int id = 0;
    try {
        id = preparedStatement.executeUpdate();
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
      
      if (id > 0) {
        ResultSet uniqueKey = null;
        try {
            uniqueKey = preparedStatement.getGeneratedKeys();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (uniqueKey.next()) {
              try {
                result = uniqueKey.getInt(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
      }
    
    return result;
  }
}
