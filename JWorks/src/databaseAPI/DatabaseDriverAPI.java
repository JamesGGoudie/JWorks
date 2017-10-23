package databaseAPI;

import java.sql.Connection;
import database.DatabaseDriver;
import exceptions.ConnectionFailedException;

public class DatabaseDriverAPI extends DatabaseDriver {
    protected static Connection connectOrCreateDataBase() {
        return DatabaseDriver.connectOrCreateDatabase();
    }
    
    protected static Connection initialize(Connection connection) {
        
        try {
            DatabaseDriver.initialize(connection);
        } catch (ConnectionFailedException e) {
            e.printStackTrace();
        }
        
        return connection;
    }
}
