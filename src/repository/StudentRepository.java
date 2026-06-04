package repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import exception.StudentNotFoundException;
import model.Student;

public class StudentRepository {
  private Map<Long, Student> students = new HashMap<>();

  public void add(Student student) {
    this.students.putIfAbsent(student.getId(), student);
  }

  public List<Student> findAll() {
    return new ArrayList<>(this.students.values());
  }

  public Optional<Student> findById(Long id) {
    return Optional.ofNullable(this.students.get(id));
  }

  public void update(Student student) {
    if (!this.students.containsKey((student.getId()))) {
      throw new StudentNotFoundException(student.getId());
    }

    this.students.put(student.getId(), student);
  }

  public void delete(Long id) {
    if (!this.students.containsKey(id))
      throw new StudentNotFoundException(id);

    this.students.remove(id);
  }
}
