package databaseAPI;

import java.sql.Connection;
import database.DatabaseDriver;
import database.DatabaseDriver;
import database.DatabaseDriverImpl;

public class DatabaseDriverAPI extends DatabaseDriverImpl{
    protected static Connection connectOrCreateDataBase() {
        return DatabaseDriver.connectOrCreateDatabase();
    }
}
