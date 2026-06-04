package model;

public class Student {
  private Long id;
  private static Long counter = 0L;
  private String name;
  private String email;
  private int age;

  public Student(int age, String name, String email) {
    counter += 1;

    this.id = counter;
    this.name = name;
    this.email = email;
    this.age = age;
  }

  @Override
  public String toString() {
    return "Student{"+
      "id=" + id +
      ", name='" + name + '\'' +
      ", email='" + email + '\'' +
      ", age=" + age +
      '}';
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


}
