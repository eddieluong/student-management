package exception;

public class StudentEmailAlreadyExistException extends RuntimeException {
  public StudentEmailAlreadyExistException() {
    super("Student email already exist");
  }
}
