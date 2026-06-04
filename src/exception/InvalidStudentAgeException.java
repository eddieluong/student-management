package exception;

public class InvalidStudentAgeException extends RuntimeException {
  public InvalidStudentAgeException() {
    super("Student age is invalid, age must be between 18 and 30");
  }
}
