package exceptions;

public class ConnectionFailedException extends Exception {
  
  private static final long serialVersionUID = 1L;

  public ConnectionFailedException() {
    super();
  }

  public ConnectionFailedException(String errorMessage) {
    super(errorMessage);
  }

}
