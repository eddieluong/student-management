package com.honglv.studentmanagement.exception;

public class StudentEmailAlreadyExistException extends RuntimeException {
  public StudentEmailAlreadyExistException() {
    super("Student email already exists");
  }
}
