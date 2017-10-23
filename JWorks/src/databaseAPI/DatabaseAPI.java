package databaseAPI;

public interface DatabaseAPI {
    /**
     * take action on the database
     * @param actObj int representing the specific type of object(Problem, Problem Set, Answer)
     * @param args arguments necess ary to complete the action
     * */
    public void actOnDatabase(int actObj, String[] args);
}

