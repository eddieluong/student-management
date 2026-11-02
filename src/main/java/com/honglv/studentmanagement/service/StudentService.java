package com.honglv.studentmanagement.service;
import java.util.List;

import com.honglv.studentmanagement.exception.StudentEmailAlreadyExistException;
import com.honglv.studentmanagement.exception.StudentNotFoundException;
import com.honglv.studentmanagement.model.Student;
import com.honglv.studentmanagement.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class StudentService {
  private final StudentRepository repository;

  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  private boolean isDuplicateEmail(String email, Long excludeId) {
    List<Student> students = repository.findAll();

    return students.stream().anyMatch(st -> st.getEmail().equals(email) && !st.getId().equals(excludeId));
  }

  public Student add(@Valid Student student) {
    if (isDuplicateEmail(student.getEmail(), null)) {
      throw new StudentEmailAlreadyExistException();
    }

    return repository.add(student);
  }

  public List<Student> getAll() {
    return repository.findAll();
  }

  public Student getDetail(Long id) {
    Student student  = repository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));

    return student;
  }

  public Student update(@Valid Student student) {

    if (this.isDuplicateEmail(student.getEmail(), student.getId())) {
      throw new StudentEmailAlreadyExistException();
    }

    return repository.update(student);
  }

  public void delete(Long id) {
    repository.delete(id);
  }
}
