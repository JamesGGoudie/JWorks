package databaseAPI;

import database.DatabaseInserter;
import exceptions.DatabaseInsertException;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseStoreAPI extends DatabaseInserter implements DatabaseAPI{
    public void actOnDatabase(int actCode, String[] args) {
        try {
            Connection connection = DatabaseDriverAPI.connectOrCreateDataBase();
            // check the type of object being acted on
            switch (actCode){
                //case when a new question is being inserted
                case 1:
                    if(args.length > 1){
                        int result = DatabaseInserter.insertProblem(1, args[0], args[1], connection);
                    }
            }
            connection.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        } catch (DatabaseInsertException e) {
            System.out.println(e.getMessage());
        }
        switch (actCode){
            case 1:

        }
    }
}
