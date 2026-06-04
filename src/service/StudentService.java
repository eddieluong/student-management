package service;
import java.util.List;
import exception.InvalidStudentAgeException;
import exception.StudentEmailAlreadyExistException;
import exception.StudentNotFoundException;
import model.Student;
import repository.StudentRepository;

public class StudentService {
  private StudentRepository repository;

  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  private boolean isDuplicateEmail(String email, Long excludeId) {
    List<Student> students = repository.findAll();

    return students.stream().anyMatch(st -> st.getEmail().equals(email) && !st.getId().equals(excludeId));
  }

  private boolean isInvalidAge(int age) {
    return age < 18 || age > 30;
  }

  public void add(Student student) {
    if (isInvalidAge(student.getAge())) {
      throw new InvalidStudentAgeException();
    }

    if (isDuplicateEmail(student.getEmail(), null)) {
      throw new StudentEmailAlreadyExistException();
    }

    repository.add(student);
  }

  public List<Student> getAll() {
    return repository.findAll();
  }

  public Student getDetail(Long id) {
    Student student  = repository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));

    return student;
  }

  public void update(Student student) {
    if (isInvalidAge(student.getAge())) {
      throw new InvalidStudentAgeException();
    }

    if (this.isDuplicateEmail(student.getEmail(), student.getId())) {
      throw new StudentEmailAlreadyExistException();
    }

    repository.update(student);
  }

  public void delete(Long id) {
    repository.delete(id);
  }
}
