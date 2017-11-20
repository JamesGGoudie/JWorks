package database;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.ConnectionFailedException;
import exceptions.DatabaseInsertException;

public class DatabaseTester {

  private static Connection connection;

  /**
   * Renames the file database file so that it doesn't get deleted during testing and initializes
   * the testing database.
   */
  @BeforeClass
  public static void setUp() {
    File oldDB = new File("jworks.db");

    if (oldDB.exists()) {
      File dest = new File("jworks_backup.db");
      oldDB.renameTo(dest);
    }

    connection = DatabaseDriver.connectOrCreateDatabase();

    try {
      DatabaseDriver.initialize(connection);
    } catch (ConnectionFailedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Reverts the directory back to how it was before by deleting the testing database and renaming
   * the original back to jworks.db.
   */
  @AfterClass
  public static void revert() {
    File oldDB = new File("jworks_backup.db");
    File dest = new File("jworks.db");
    dest.delete();
    oldDB.renameTo(dest);
  }

  @Test
  public void insertStudentBasic() {
    int studentNumber = 1;
    String name = "Jim";
    String email = "james.mail";
    String password = "SECRET";

    boolean actual = false;

    try {
      actual = DatabaseInserter.insertStudent(studentNumber, name, email, password,
          connection);
    } catch (DatabaseInsertException e) {
    }

    boolean expected = true;

    assertEquals(expected, actual);
  }

  @Test
  public void insertStudentDuplicate() {
    int studentNumber = 2;
    String name = "Jim";
    String email = "james.mail";
    String password = "SECRET";

    boolean actual = false;

    try {
      boolean result = DatabaseInserter.insertStudent(studentNumber, name, email, password,
          connection);

      if (result) {
        actual = DatabaseInserter.insertStudent(studentNumber, name, email, password,
            connection);
      } else {
        fail("Could not insert a basic student.");
      }
    } catch (DatabaseInsertException e) {
      assertTrue(e.getMessage() == "Failed to insert student into the database.");
    }

    boolean expected = false;

    assertEquals(expected, actual);
  }

  @Test
  public void insertProfessorBasic() {
    int instructorNumber = 1;
    String name = "Jim";
    String email = "james.mail";
    String password = "SECRET";

    boolean actual = false;

    try {
      actual = DatabaseInserter.insertInstructor(instructorNumber, name, email, password,
          connection);
    } catch (DatabaseInsertException e) {
    }

    boolean expected = true;

    assertEquals(expected, actual);
  }

  @Test
  public void insertInstructorDuplicate() {
    int instructorNumber = 2;
    String name = "Jim";
    String email = "james.mail";
    String password = "SECRET";

    boolean actual = false;

    try {
      boolean result = DatabaseInserter.insertInstructor(instructorNumber, name, email, password,
          connection);

      if (result) {
        actual = DatabaseInserter.insertInstructor(instructorNumber, name, email, password,
            connection);
      } else {
        fail("Could not insert a basic instructor.");
      }
    } catch (DatabaseInsertException e) {
      assertTrue(e.getMessage() == "Failed to insert instructor into the database.");
    }

    boolean expected = false;

    assertEquals(expected, actual);
  }

}
