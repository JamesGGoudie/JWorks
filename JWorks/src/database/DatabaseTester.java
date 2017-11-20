package database;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.ConnectionFailedException;
import exceptions.DatabaseInsertException;
import exceptions.DatabaseSelectException;

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
  
  /**
   * Tests that we can insert a student into the database and get the exact same information from
   * the database.
   */
  @Test
  public void insertAndRetrieveStudentBasic() {
    int studentNumber = 1;
    String name = "Jim";
    String email = "james.mail";
    String password = "SECRET";
    
    try {
      boolean actual = DatabaseInserter.insertStudent(studentNumber, name, email, password,
          connection);
      
      // Make sure that the student was inserted into the database.
      assertTrue(actual);
      
      ResultSet results = DatabaseSelector.getStudent(studentNumber, connection);
      
      // Make sure that the the data in the student table is correct.
      assertEquals(name, results.getString(2));
      assertEquals(email, results.getString(3));
      assertEquals(password, results.getString(4));
      
    } catch (DatabaseInsertException | DatabaseSelectException | SQLException e) {
      fail();
    }
  }
  
  /**
   * Tests that if we attempt to insert a student with an ID that is already present in the
   * database, a DatabaseInsertException will be thrown.
   */
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
      
      // Make sure that the student was inserted into the database.
      assertTrue(result);
      
      actual = DatabaseInserter.insertStudent(studentNumber, name, email, password, connection);
    } catch (DatabaseInsertException e) {
      // Make sure that we are getting the correct error message.
      assertTrue(e.getMessage() == "Failed to insert student into the database.");
    }
    
    assertFalse(actual);
  }
  
  /**
   * Tests that we can insert a instructor into the database and retrieve the exact same data from
   * the database.
   */
  @Test
  public void insertAndRetrieveInstructor() {
    int instructorID = 1;
    String name = "Jim";
    String email = "james.mail";
    String password = "SECRET";
    
    try {
      boolean actual = DatabaseInserter.insertInstructor(instructorID, name, email, password,
          connection);
      
      // Make sure that the instructor was inserted into the database.
      assertTrue(actual);
      
      ResultSet results = DatabaseSelector.getInstructor(instructorID, connection);
      
      // Make sure that all of the data in the instructors table is correct.
      assertEquals(name, results.getString(2));
      assertEquals(email, results.getString(3));
      assertEquals(password, results.getString(4));
    } catch (DatabaseInsertException | DatabaseSelectException | SQLException e) {
      fail();
    }
  }

  /**
   * Tests that if we attempt to insert an instructor with an ID that is already present in the
   * database, a DatabaseInsertException will be thrown.
   */
  @Test
  public void insertInstructorDuplicate() {
    int instructorID = 2;
    String name = "Jim";
    String email = "james.mail";
    String password = "SECRET";

    boolean actual = false;

    try {
      boolean result = DatabaseInserter.insertInstructor(instructorID, name, email, password,
          connection);
      
      // Make sure that the first instructor was added to the database.
      assertTrue(result);
      
      actual = DatabaseInserter.insertInstructor(instructorID, name, email, password,
          connection);
    } catch (DatabaseInsertException e) {
      // Make sure that we are getting the correct error message.
      assertTrue(e.getMessage() == "Failed to insert instructor into the database.");
    }
    
    assertFalse(actual);
  }
  
  /**
   * Tests that we can insert a problem into the database along with the instructor that created it
   * and get the exact same data for the problem from the database and that we can get the ID of
   * the creator of the problem.
   */
  @Test
  public void insertAndRetrieveProblem() {
    int questionType = 1;
    String question = "1 + 1";
    String answer = "2";
    
    int instructorID = 3;
    String name = "Jim";
    String email = "james.mail";
    String password = "SECRET";
    
    try {
      boolean instructorExists = DatabaseInserter.insertInstructor(instructorID, name, email,
          password, connection);
      
      // Make sure that the instructor was added to the database.
      assertTrue(instructorExists);
      
      int result = DatabaseInserter.insertProblem(questionType, question, answer, instructorID,
          connection);
      
      // Make sure that the problem was inserted into the database.
      assertTrue(result > 0);
      
      ResultSet results = DatabaseSelector.getSingleProblem(result, connection);
      
      // Make sure that all of the data from the problems table is correct.
      assertEquals(questionType, results.getInt(2));
      assertEquals(question, results.getString(3));
      assertEquals(answer, results.getString(4));
      
      int creatorID = DatabaseSelector.getProblemCreator(result, connection);
      
      // Make sure that the creator ID retrieved is valid and corresponds to the professor.
      assertTrue(creatorID > 0);
      assertEquals(instructorID, creatorID);
      
    } catch (DatabaseInsertException | DatabaseSelectException | SQLException e) {
      fail();
    }
  }
  
  /**
   * Tests that we can insert a problem set into the database along with the problems it contains
   * and the instructor that created it. Also tests that we can retrieve that data directly
   * relating to the problem set as well as the ID's of the problems contained and the ID of the
   * instructor who created it.
   */
  @Test
  public void insertAndRetrieveProblemSet() {
    int questionType = 1;
    String questionOne = "1 + 1";
    String answerOne = "2";
    String questionTwo = "1 + 2";
    String answerTwo = "3";
    String questionThree = "2 + 2";
    String answerThree = "4";
    
    int instructorID = 4;
    String name = "Jim";
    String email = "james.mail";
    String password = "SECRET";
    
    int maxAttempts = 5;
    
    Date startTime = new Date(1000);
    Date endTime = new Date(1000000);
    
    try {
      boolean instructorExists = DatabaseInserter.insertInstructor(instructorID, name, email,
          password, connection);
      
      assertTrue(instructorExists);
      
      int problemIDOne = DatabaseInserter.insertProblem(questionType, questionOne, answerOne,
          instructorID, connection);

      // Make sure that the problem was inserted into the database.
      assertTrue(problemIDOne > 0);
      
      int problemIDTwo = DatabaseInserter.insertProblem(questionType, questionTwo, answerTwo,
          instructorID, connection);

      // Make sure that the problem was inserted into the database.
      assertTrue(problemIDTwo > 0);
      
      int problemIDThree = DatabaseInserter.insertProblem(questionType, questionThree, answerThree,
          instructorID, connection);
      
      // Make sure that the problem was inserted into the database.
      assertTrue(problemIDThree > 0);
      
      int[] problemIDs = {problemIDOne, problemIDTwo, problemIDThree};
      
      int problemSetKey = DatabaseInserter.insertProblemSet(maxAttempts, problemIDs, startTime,
          endTime, instructorID, connection);
      
      // Make sure that the problem set was inserted into the database.
      assertTrue(problemSetKey > 0);
      
      ResultSet problemSetRaw = DatabaseSelector.getProblemSet(problemSetKey, connection);
      
      // Make sure that the data from the problem set table is correct.
      assertEquals(maxAttempts, problemSetRaw.getInt(2));
      assertEquals(startTime.getTime() / 1000L, problemSetRaw.getLong(3));
      assertEquals(endTime.getTime() / 1000L, problemSetRaw.getLong(4));
      
      ResultSet problemsRaw = DatabaseSelector.getProblemsInProblemSet(problemSetKey, connection);
      
      // Make sure that the data from the problem-problemSet relationship table is correct.
      problemsRaw.next();
      assertEquals(problemIDOne, problemsRaw.getInt(1));
      problemsRaw.next();
      assertEquals(problemIDTwo, problemsRaw.getInt(1));
      problemsRaw.next();
      assertEquals(problemIDThree, problemsRaw.getInt(1));

      int creatorID = DatabaseSelector.getProblemSetCreator(problemSetKey, connection);
      
      // Make sure that the creator's ID is valid and matches with the instructor's ID.
      assertTrue(creatorID > 0);
      assertEquals(instructorID, creatorID);
      
    } catch (DatabaseInsertException | DatabaseSelectException | SQLException e) {
      fail();
    }
  }

  /**
   * Tests that we can retrieve and amount of attempts remaining from the database as well as
   * modify how many attempts are left if we insert the problem set into the database before we
   * insert the student.
   */
  @Test
  public void modifyAndRetrieveAttemptsRemainingProblemSetFirst() {
    int questionType = 1;
    String questionOne = "1 + 1";
    String answerOne = "2";
    
    int instructorID = 5;
    String nameInstructor = "Jim";
    String emailInstructor = "james.mail";
    String passwordInstructor = "SECRET";
    
    int studentNumber = 3;
    String nameStudent = "Tim";
    String emailStudent = "Tames.mail";
    String passwordStudent = "SOOPE4-D0OP3R_SECRET";
    
    int maxAttempts = 5;
    int attemptsRemaining = 4;
    
    Date startTime = new Date(1000);
    Date endTime = new Date(1000000);
    
    try {
      boolean instructorExists = DatabaseInserter.insertInstructor(instructorID, nameInstructor,
          emailInstructor, passwordInstructor, connection);
      
      assertTrue(instructorExists);
      
      int problemIDOne = DatabaseInserter.insertProblem(questionType, questionOne, answerOne,
          instructorID, connection);

      // Make sure that the problem was inserted into the database.
      assertTrue(problemIDOne > 0);
      
      int[] problemIDs = {problemIDOne};
      
      int problemSetKey = DatabaseInserter.insertProblemSet(maxAttempts, problemIDs, startTime,
          endTime, instructorID, connection);
      
      // Make sure that the problem set was inserted into the database.
      assertTrue(problemSetKey > 0);
      
      boolean studentExists = DatabaseInserter.insertStudent(studentNumber, nameStudent, emailStudent,
          passwordStudent, connection);
      
      // Make sure that the student was inserted into the database.
      assertTrue(studentExists);
      
      int initialResult = DatabaseSelector.getAttemptsRemaining(studentNumber, problemSetKey,
          connection);
      
      // Make sure that the initial amount of attempts is correct.
      assertEquals(maxAttempts, initialResult);
      
      boolean updateResult = DatabaseUpdater.updateAttemptsRemaining(problemSetKey, studentNumber,
          attemptsRemaining, connection);
      
      // Make sure that we were able to modify the database.
      assertTrue(updateResult);
      
      int finalResult = DatabaseSelector.getAttemptsRemaining(studentNumber, problemSetKey,
          connection);
      
      // Make sure that the new amount of attempts is correct.
      assertEquals(attemptsRemaining, finalResult);
      
    } catch (DatabaseInsertException | DatabaseSelectException e) {
      fail();
    }
  }
  
  /**
   * Tests that we can retrieve and amount of attempts remaining from the database as well as
   * modify how many attempts are left if we insert the student into the database before we insert
   * the problem set.
   */
  @Test
  public void modifyAndRetrieveAttemptsRemainingStudentFirst() {
    int questionType = 1;
    String questionOne = "1 + 1";
    String answerOne = "2";
    
    int instructorID = 6;
    String nameInstructor = "Jim";
    String emailInstructor = "james.mail";
    String passwordInstructor = "SECRET";
    
    int studentNumber = 4;
    String nameStudent = "Tim";
    String emailStudent = "Tames.mail";
    String passwordStudent = "SOOPE4-D0OP3R_SECRET";
    
    int maxAttempts = 5;
    int attemptsRemaining = 4;
    
    Date startTime = new Date(1000);
    Date endTime = new Date(1000000);
    
    try {
      boolean studentExists = DatabaseInserter.insertStudent(studentNumber, nameStudent, emailStudent,
          passwordStudent, connection);
      
      // Make sure that the student was inserted into the database.
      assertTrue(studentExists);
      
      boolean instructorExists = DatabaseInserter.insertInstructor(instructorID, nameInstructor,
          emailInstructor, passwordInstructor, connection);
      
      assertTrue(instructorExists);
      
      int problemIDOne = DatabaseInserter.insertProblem(questionType, questionOne, answerOne,
          instructorID, connection);

      // Make sure that the problem was inserted into the database.
      assertTrue(problemIDOne > 0);
      
      int[] problemIDs = {problemIDOne};
      
      int problemSetKey = DatabaseInserter.insertProblemSet(maxAttempts, problemIDs, startTime,
          endTime, instructorID, connection);
      
      // Make sure that the problem set was inserted into the database.
      assertTrue(problemSetKey > 0);
      
      int initialResult = DatabaseSelector.getAttemptsRemaining(studentNumber, problemSetKey,
          connection);
      
      // Make sure that the initial amount of attempts is correct.
      assertEquals(maxAttempts, initialResult);
      
      boolean updateResult = DatabaseUpdater.updateAttemptsRemaining(problemSetKey, studentNumber,
          attemptsRemaining, connection);
      
      // Make sure that we were able to modify the database.
      assertTrue(updateResult);
      
      int finalResult = DatabaseSelector.getAttemptsRemaining(studentNumber, problemSetKey,
          connection);
      
      // Make sure that the new amount of attempts is correct.
      assertEquals(attemptsRemaining, finalResult);
      
    } catch (DatabaseInsertException | DatabaseSelectException e) {
      fail();
    }
  }

  /**
   * Tests that we can store a students attempt to a problem set in the database and retrieving it
   * results in the exact same data passed in.
   */
  @Test
  public void insertAndRetrievePreviousAttempt() {
    int questionType = 1;
    String questionOne = "1 + 1";
    String answerOne = "2";
    String questionTwo = "1 + 2";
    String answerTwo = "3";
    String questionThree = "2 + 2";
    String answerThree = "4";
    
    int instructorID = 7;
    String nameInstructor = "Jim";
    String emailInstructor = "james.mail";
    String passwordInstructor = "SECRET";
    
    int maxAttempts = 5;
    
    Date startTime = new Date(1000);
    Date endTime = new Date(1000000);
    
    int studentNumber = 5;
    String nameStudent = "Jim";
    String emailStudent = "james.mail";
    String passwordStudent = "SECRET";
    
    String studentAnswerOne = "7";
    String studentAnswerTwo = "8";
    String studentAnswerThree = "9";
    
    String[] studentAnswers = {studentAnswerOne, studentAnswerTwo, studentAnswerThree};
    
    int attemptNumber = 1;
    
    try {
      boolean instructorExists = DatabaseInserter.insertInstructor(instructorID, nameInstructor,
          emailInstructor, passwordInstructor, connection);
      
      assertTrue(instructorExists);
      
      int problemIDOne = DatabaseInserter.insertProblem(questionType, questionOne, answerOne,
          instructorID, connection);

      // Make sure that the problem was inserted into the database.
      assertTrue(problemIDOne > 0);
      
      int problemIDTwo = DatabaseInserter.insertProblem(questionType, questionTwo, answerTwo,
          instructorID, connection);

      // Make sure that the problem was inserted into the database.
      assertTrue(problemIDTwo > 0);
      
      int problemIDThree = DatabaseInserter.insertProblem(questionType, questionThree, answerThree,
          instructorID, connection);
      
      // Make sure that the problem was inserted into the database.
      assertTrue(problemIDThree > 0);
      
      int[] problemIDs = {problemIDOne, problemIDTwo, problemIDThree};
      
      int problemSetKey = DatabaseInserter.insertProblemSet(maxAttempts, problemIDs, startTime,
          endTime, instructorID, connection);
      
      // Make sure that the problem set was inserted into the database.
      assertTrue(problemSetKey > 0);

      boolean studentExists = DatabaseInserter.insertStudent(studentNumber, nameStudent, emailStudent,
          passwordStudent, connection);
      
      // Make sure that the student was inserted into the database.
      assertTrue(studentExists);
      
      boolean insertedAttempts = DatabaseInserter.insertStudentsAttempt(studentNumber, attemptNumber, problemSetKey, problemIDs, studentAnswers, connection);
      
      // Make sure that the attempt was inserted.
      assertTrue(insertedAttempts);
      
      ResultSet results = DatabaseSelector.getStudentsResults(studentNumber, problemSetKey, attemptNumber, connection);
      
      // Make sure that the student results retrieved matches what was inserted.
      results.next();
      assertEquals(problemIDOne, results.getInt(1));
      assertEquals(studentAnswerOne, results.getString(2));
      results.next();
      assertEquals(problemIDTwo, results.getInt(1));
      assertEquals(studentAnswerTwo, results.getString(2));
      results.next();
      assertEquals(problemIDThree, results.getInt(1));
      assertEquals(studentAnswerThree, results.getString(2));
      
    } catch (DatabaseInsertException | DatabaseSelectException | SQLException e) {
      fail();
    }
  }

  /**
   * Tests that we can retrieve the problem IDs of all of the problems inserted into the database
   * by an instructor.
   */
  @Test
  public void retrieveInstructorsProblems() {
    int questionType = 1;
    String questionOne = "1 + 1";
    String answerOne = "2";
    String questionTwo = "1 + 2";
    String answerTwo = "3";
    String questionThree = "2 + 2";
    String answerThree = "4";
    
    int instructorID = 8;
    String name = "Jim";
    String email = "james.mail";
    String password = "SECRET";
    
    try {
      boolean instructorExists = DatabaseInserter.insertInstructor(instructorID, name, email,
          password, connection);
      
      assertTrue(instructorExists);
      
      int problemIDOne = DatabaseInserter.insertProblem(questionType, questionOne, answerOne,
          instructorID, connection);

      // Make sure that the problem was inserted into the database.
      assertTrue(problemIDOne > 0);
      
      int problemIDTwo = DatabaseInserter.insertProblem(questionType, questionTwo, answerTwo,
          instructorID, connection);

      // Make sure that the problem was inserted into the database.
      assertTrue(problemIDTwo > 0);
      
      int problemIDThree = DatabaseInserter.insertProblem(questionType, questionThree, answerThree,
          instructorID, connection);
      
      // Make sure that the problem was inserted into the database.
      assertTrue(problemIDThree > 0);
      
      ResultSet results = DatabaseSelector.getInstructorsProblems(instructorID, connection);
      
      // Make sure that all of the ID's retrieved from the database correspond to those inserted.
      results.next();
      assertEquals(problemIDOne, results.getInt(1));
      results.next();
      assertEquals(problemIDTwo, results.getInt(1));
      results.next();
      assertEquals(problemIDThree, results.getInt(1));
    } catch (DatabaseInsertException | DatabaseSelectException | SQLException e) {
      fail();
    }
  }
  
  /**
   * Tests that we can retrieve the problem set IDs of all of the problems inserted into the
   * database by an instructor.
   */
  @Test
  public void retrieveInstructorsProblemSets() {
    int questionType = 1;
    String questionOne = "1 + 1";
    String answerOne = "2";
    String questionTwo = "1 + 2";
    String answerTwo = "3";
    String questionThree = "2 + 2";
    String answerThree = "4";
    
    int instructorID = 9;
    String name = "Jim";
    String email = "james.mail";
    String password = "SECRET";
    
    int maxAttempts = 5;
    
    Date startTime = new Date(1000);
    Date endTime = new Date(1000000);
    
    try {
      boolean instructorExists = DatabaseInserter.insertInstructor(instructorID, name, email,
          password, connection);
      
      assertTrue(instructorExists);
      
      int problemIDOne = DatabaseInserter.insertProblem(questionType, questionOne, answerOne,
          instructorID, connection);

      // Make sure that the problem was inserted into the database.
      assertTrue(problemIDOne > 0);
      
      int problemIDTwo = DatabaseInserter.insertProblem(questionType, questionTwo, answerTwo,
          instructorID, connection);

      // Make sure that the problem was inserted into the database.
      assertTrue(problemIDTwo > 0);
      
      int problemIDThree = DatabaseInserter.insertProblem(questionType, questionThree, answerThree,
          instructorID, connection);
      
      // Make sure that the problem was inserted into the database.
      assertTrue(problemIDThree > 0);
      
      int[] problemIDs = {problemIDOne, problemIDTwo, problemIDThree};
      
      int problemSetIDOne = DatabaseInserter.insertProblemSet(maxAttempts, problemIDs, startTime,
          endTime, instructorID, connection);
      
      // Make sure that the problem set was added to the database.
      assertTrue(problemSetIDOne > 0);
      
      int problemSetIDTwo = DatabaseInserter.insertProblemSet(maxAttempts, problemIDs, startTime,
          endTime, instructorID, connection);
      
      // Make sure that the problem set was added to the database.
      assertTrue(problemSetIDTwo > 0);
      
      int problemSetIDThree = DatabaseInserter.insertProblemSet(maxAttempts, problemIDs, startTime,
          endTime, instructorID, connection);
      
      // Make sure that the problem set was added to the database.
      assertTrue(problemSetIDThree > 0);

      ResultSet results = DatabaseSelector.getInstructorsProblemSets(instructorID, connection);
      
      // Make sure that all of the ID's retrieved from the database correspond to those inserted.
      results.next();
      assertEquals(problemSetIDOne, results.getInt(1));
      results.next();
      assertEquals(problemSetIDTwo, results.getInt(1));
      results.next();
      assertEquals(problemSetIDThree, results.getInt(1));
    } catch (DatabaseInsertException | DatabaseSelectException | SQLException e) {
      fail();
    }
  }
}
