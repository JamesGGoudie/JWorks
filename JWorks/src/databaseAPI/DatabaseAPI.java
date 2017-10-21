package JWorks.databaseAPI;

import JWorks.database.*;
import java.sql.Connection;

/**
 *
 */
public interface DatabaseAPI extends DatabaseDriver {
    /**
     * take action on the database
     * @param actCode int representing the specific action to be taken
     * @param args arguments necess ary to complete the action
     * */
    public void actOnDatabase(int actCode, String[] args);
}