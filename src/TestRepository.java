import java.util.List;
import exception.StudentNotFoundException;
import model.Student;
import repository.StudentRepository;

public class TestRepository {
  private static Student addedStudent = null;

  public static void main(String[] args) {
    StudentRepository studentRepo = new StudentRepository();

    // add new student
    try {
      Student student1 = new Student(24, "Eddie", "sweetkurt@gmail.com");
      addedStudent = studentRepo.add(student1);

      System.out.println("Test thêm người dùng thành công!");
    } catch (RuntimeException e) {
      System.out.println("Test thêm student lỗi " + e.getCause());
    }

    // get all students
    try {
      List<Student> list = studentRepo.findAll();
      list.forEach(System.out::println);
      System.out.println("Test get all student thành công!");

    } catch (RuntimeException e) {
      System.out.print("Test get all student lỗi " + e.getCause());
    }

    // get detail
    try {
      Student st = studentRepo.findById(addedStudent.getId()).orElseThrow(() -> new StudentNotFoundException(addedStudent.getId()));
      System.out.println(st);
    } catch (RuntimeException e) {
      System.out.println("Test get detail student lỗi " + e.getCause());
    }

    // update
    try {
      Student updatedUser = new Student(25, "Eddie 2", "hongluong296@gmail.com");
      updatedUser.setId(addedStudent.getId());

      Student updated = studentRepo.update(updatedUser);
      System.out.println("Update user thành công" +  updated);
    } catch (RuntimeException e) {
      System.out.println("Test update user lỗi! " + e.getCause());
    }

    // xóa
    try {
      Long id = studentRepo.delete(addedStudent.getId());
      System.out.println("Xóa user có id " + id + " thành công!");

    } catch(RuntimeException e) {
      System.out.println("Test xóa student lỗi " + e.getStackTrace());
    }
  }
}
