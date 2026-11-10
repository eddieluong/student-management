package com.honglv.studentmanagement.service;
import java.util.ArrayList;
import java.util.List;

import com.honglv.studentmanagement.exception.StudentEmailAlreadyExistException;
import com.honglv.studentmanagement.exception.StudentNotFoundException;
import com.honglv.studentmanagement.model.Student;
import com.honglv.studentmanagement.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@Transactional
public class StudentService {
  private final StudentRepository repository;

  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  public Student add(@Valid Student student) {
//    if (repository.existsByEmail(student.getEmail())) {
//      throw new StudentEmailAlreadyExistException();
//    }

    student.setId(null);
    return repository.save(student);
  }

  public List<Student> addAll(List<Student> students) {
    List<Student> results = new ArrayList<>();
    for (Student student : students) {
      if (repository.existsByEmail(student.getEmail())) {
        throw new StudentEmailAlreadyExistException();
      }

      student.setId(null);
      results.add(repository.save(student));
    }

    return results;
  }

  @Transactional(readOnly = true)
  public List<Student> getAll() {
    return repository.findAll();
  }

  @Transactional(readOnly = true)
  public Student getDetail(Long id) {
    return repository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
  }

  public Student update(@Valid Student student) {
    if (!repository.existsById(student.getId())) {
      throw new StudentNotFoundException(student.getId());
    }

    if (repository.existsByEmailAndIdNot(student.getEmail(), student.getId())) {
      throw new StudentEmailAlreadyExistException();
    }


    return repository.save(student);
  }

  public void delete(Long id) {
    if (!repository.existsById(id)) {
      throw new StudentNotFoundException(id);
    }

    repository.deleteById(id);
  }
}
