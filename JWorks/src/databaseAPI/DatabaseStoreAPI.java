package databaseAPI;

import database.DatabaseInserter;
import exceptions.DatabaseInsertException;
import javafx.scene.input.DataFormat;
import models.*;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseStoreAPI extends DatabaseInserter implements DatabaseAPI{
    private Connection connection;
    /**
     * Inserts into the problems table in the JWorks database
     * @param newProblem Problem object that is to be inserted into the database
     * @return the PrimaryKey of the problem in the table
     * @throws DatabaseInsertException
     * @throws SQLException
     */
    public int actOnDatabase(Problem newProblem) throws DatabaseInsertException, SQLException{
        connection = DatabaseDriverAPI.connectOrCreateDataBase();
        //store the primary key of row inserted
        int result;
        result = DatabaseInserter.insertProblem(1, newProblem.getQuestion(), newProblem.getAnswer(), connection);
        return result;
    }

    /**
     * Inserts into the problem sets table in the JWorks database
     * @param newPSet Problem set objects whose attribute is to be stored into the database
     * @return Primary key of the ProblemSet in the table
     * @throws DatabaseInsertException
     * @throws SQLException
     */
    public int actOnDatabase(ProblemSet newPSet) throws DatabaseInsertException, SQLException{
        connection = DatabaseDriverAPI.connectOrCreateDataBase();
        //store the primary key of row inserted
        int result;
        int[] pIDs = new int[newPSet.getQuestions().size()];
        for (int i = 0; i < newPSet.getQuestions().size(); i++){
            pIDs[i] = (newPSet.getQuestions().get(i)).getId();
        }
        result = DatabaseInserter.insertProblemSet(newPSet.getMaxAttempts(), pIDs, newPSet.getStartTime(), newPSet.getEndTime(), connection);
        result = 0;
        return result;
    }

    /**
     * Insert new Student User
     * @param newStudent instance of new Student who is going to be registered into the database
     * @return student number for successful insertion, -1 for failed insertion
     * @throws DatabaseInsertException
     * @throws SQLException
     */
    public int actOnDatabase(Student newStudent) throws DatabaseInsertException, SQLException{
        connection = DatabaseDriverAPI.connectOrCreateDataBase();
        //store the primary key of row inserted
        int result;
        boolean test = DatabaseInserter.insertStudent(newStudent.getStudentNumber(), newStudent.getName(), newStudent.getEmailAddress(), connection);
        if (test){
            result = newStudent.getStudentNumber();
        } else{
            result = -1;
        }
        return result;
    }

    @Override
    public void actOnDatabase(int actObj, String[] args) {
        connection = DatabaseDriverAPI.connectOrCreateDataBase();
    }
}
