package databaseAPI;

import database.DatabaseInserter;
import io.OutputGen;
import io.UI;
import exceptions.DatabaseInsertException;
import io.OutputGenerator;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseStoreAPI extends DatabaseInserter implements DatabaseAPI{
    public void actOnDatabase(int actObj, String[] args) {
        // pass output message here
        OutputGenerator toUI = new OutputGenerator();
        try {
            Connection connection = DatabaseDriverAPI.connectOrCreateDataBase();
            //store the primary key of row inserted
            int result;
            // store number of arguments supplied
            int lenArg = args.length;
            // check the type of object being acted on
            switch (actObj){
                //case when a new question is being inserted
                case 1:
                    if(lenArg > 1){
                        result = DatabaseInserter.insertProblem(1, args[0], args[1], connection);
                        if(result != -1){
                            toUI.output("A problem has been created with unique key: "+result);
                        }
                    }
                    break;
                //case when problem set if being inserted
                case 2:
                    if(lenArg > 1){
                        int maxAttempt = Integer.parseInt(args[0]);
                        int[] problemKeys = new int[lenArg];
                        for(int i = 0; i < lenArg -1; i++){
                            problemKeys[i] = Integer.parseInt(args[i+1]);
                        }
                        result = DatabaseInserter.insertProblemSet(maxAttempt, problemKeys, connection);
                        if(result != -1){
                            toUI.output("A new problem set has been created with key: "+result);
                        }
                    }
                    break;
                case 3:
                    if(lenArg > 2){
                        int sNumber = Integer.parseInt(args[0]);
                        String sName = args[1];
                        String sEmail = args[2];
                        if(DatabaseInserter.insertStudent(sNumber, sName, sEmail,connection)){
                            toUI.output("User with student number: "+sNumber+" name: "+sName+" and email: "+sEmail+" has been registered as a student");
                        } else{
                            throw new DatabaseInsertException("User with student number: "+sNumber+" already exists");
                        }
                    }
                    break;
            }
            connection.close();
        }catch (SQLException e){
            toUI.output(e.getMessage());
        } catch (DatabaseInsertException e) {
            toUI.output(e.getMessage());
        }
    }
}
