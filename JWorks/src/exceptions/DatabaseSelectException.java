package exceptions;

public class DatabaseSelectException extends Exception {
  
  private static final long serialVersionUID = 1L;

  public DatabaseSelectException() {
    super();
  }

  public DatabaseSelectException(String errorMessage) {
    super(errorMessage);
  }

}
