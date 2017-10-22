package databaseAPI;

import java.sql.Connection;
import dabatase.*;

public class DatabaseDriverAPI extends DatabaseDriver{
    protected static Connection connectOrCreateDataBase() {
        return DatabaseDriver.connectOrCreateDataBase();
    }
}
