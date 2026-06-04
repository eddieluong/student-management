package controller;
import java.util.List;
import java.util.Scanner;
import model.Student;
import service.StudentService;

public class StudentController {
  private StudentService service;
  private Scanner scanner = new Scanner(System.in);

  public StudentController(StudentService service) {
    this.service = service;
  }

  public void start() {
    while (true) {
      System.out.println("=== STUTENĐ MANAGEMENT ===");
      System.out.println("1. Xem danh sách");
      System.out.println("2. Xem chi tiết học sinh");
      System.out.println("3. Thêm học sinh mới");
      System.out.println("4. Xóa thông tin học sinh");
      System.out.println("0. Thoát");

      int choice = scanner.nextInt();

      switch (choice) {
        case 1:
          List<Student> students = service.getAll();
          students.forEach(st -> System.out.println(st));
          break;

        case 2:
          System.out.print("Nhập student id:");
          Long id = scanner.nextLong();
          Student studentDetail = service.getDetail(id);

          System.out.println(studentDetail);

          break;

        case 3:
          System.out.print("Nhập tên: ");
          String name = scanner.next();
          System.out.print("Nhập email: ");
          String email = scanner.next();
          System.out.print("Nhập tuổi: ");
          int age = scanner.nextInt();

          Student student = new Student(age, name, email);
          service.add(student);
          System.out.println("Thêm thành công!");

          break;

        case 4:
          System.out.print("Nhập id học sinh cần xóa: ");
          Long idDelete = scanner.nextLong();
          service.delete(idDelete);

          break;

        case 0:
          System.out.println("Tạm biệt!");
          return;

        default:
          System.out.println("Lựa chọn không hợp lệ!");
          break;
      }
    }
  }
}
