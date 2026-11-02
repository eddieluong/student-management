package com.honglv.studentmanagement.repository;
import com.honglv.studentmanagement.exception.StudentNotFoundException;
import com.honglv.studentmanagement.model.Student;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository {
  private final DataSource dataSource;

  public StudentRepository(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  private Student mapRow(ResultSet rs) throws SQLException {
    Student student = new Student(rs.getInt("age"), rs.getString("name"), rs.getString("email"));
    student.setId(rs.getLong("id"));

    return student;
  }

  public List<Student> findAll() {
    String sql = "SELECT * FROM students";
    List<Student> result = new ArrayList<Student>();

    try (
            Connection conn = dataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
    ) {
      ResultSet rs = statement.executeQuery();

      while(rs.next()) {
        result.add(mapRow(rs));
      }

      return result;

    } catch (SQLException e) {
      throw new RuntimeException("Lấy danh sách học sinh thất bại", e);
    }
  }

  public Optional<Student> findById(Long id) {
    String sql = "SELECT * FROM students WHERE id = ?";

    try (
      Connection conn = dataSource.getConnection();
      PreparedStatement statement = conn.prepareStatement(sql);
    ) {
      statement.setLong(1, id);
      ResultSet rs = statement.executeQuery();

      if (rs.next()) {
        return Optional.of(mapRow(rs));
      }
      return Optional.empty();

    } catch (SQLException e) {
      throw new RuntimeException("Lấy thông tin học sinh chi tiết thất bại", e);
    }

  }

  // return user id deleted
  public Long delete(Long id) {
    String sql = "DELETE FROM students WHERE id = ?";

    try (
      Connection conn = dataSource.getConnection();
      PreparedStatement statement = conn.prepareStatement(sql);
    ) {
      statement.setLong(1, id);
      int rowDeleted = statement.executeUpdate();

      if (rowDeleted > 0) {
        return id;
      }

      throw new StudentNotFoundException(id);
    } catch (SQLException e) {
      throw new RuntimeException("Xóa học sinh thất bại", e);
    }
  }

  public Student add(Student student) {
    String sql = "INSERT INTO students(age, name, email) VALUES (?, ?, ?) RETURNING *";

    try (
      Connection conn = dataSource.getConnection();
      PreparedStatement statement = conn.prepareStatement(sql);
    ) {
      statement.setInt(1, student.getAge());
      statement.setString(2, student.getName());
      statement.setString(3, student.getEmail());

      ResultSet rs = statement.executeQuery();

      if (rs.next()) {
        return mapRow(rs);
      }

      throw new RuntimeException("Thêm học sinh mới thất bại");
    } catch (SQLException e) {
      throw new RuntimeException("Thêm học sinh mới thất bại", e);
    }
  }

  public Student update(Student student) {
    String sql = "UPDATE students SET age = ?, name = ?, email = ? WHERE id = ? RETURNING *";

    try (
      Connection conn = dataSource.getConnection();
      PreparedStatement statement = conn.prepareStatement(sql);
    ) {
      statement.setInt(1, student.getAge());
      statement.setString(2, student.getName());
      statement.setString(3, student.getEmail());
      statement.setLong(4, student.getId());

      ResultSet rs = statement.executeQuery();

      if (rs.next()) {
        return mapRow(rs);
      }

      throw new StudentNotFoundException(student.getId());
    } catch (SQLException e) {
      throw new RuntimeException("Lỗi cập nhật thông tin học sinh", e);
    }
  }
}
