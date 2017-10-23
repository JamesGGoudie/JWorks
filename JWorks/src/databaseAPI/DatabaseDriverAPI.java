package databaseAPI;

import java.sql.Connection;
import database.DatabaseDriver;
import database.DatabaseDriver;

public class DatabaseDriverAPI extends DatabaseDriver{
    protected static Connection connectOrCreateDataBase() {
        return DatabaseDriver.connectOrCreateDatabase();
    }
}
