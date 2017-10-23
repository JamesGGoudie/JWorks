package exceptions;

public class DatabaseInsertException extends Exception {
  
  private static final long serialVersionUID = 1L;

  public DatabaseInsertException() {
    super();
  }

  public DatabaseInsertException(String errorMessage) {
    super(errorMessage);
  }

}
