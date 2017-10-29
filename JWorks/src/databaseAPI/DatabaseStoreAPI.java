package databaseAPI;

import database.DatabaseInserter;
import database.DatabaseSelector;
import exceptions.DatabaseInsertException;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseStoreAPI extends DatabaseInserter implements DatabaseAPI{
    public void actOnDatabase(int actObj, String[] args) {
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
                    }
                    break;
            }
            connection.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        } catch (DatabaseInsertException e) {
            System.out.println(e.getMessage());
        }
    }
}
