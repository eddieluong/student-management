package com.honglv.studentmanagement.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Student {
  private Long id;
  @NotBlank(message = "Name is required")
  private String name;
  @NotBlank(message = "Email is required")
  @Email(message = "Email format is invalid")
  private String email;
  @Min(value = 18, message = "Age must be at least 18")
  @Max(value = 30, message = "Age must be at most 30")
  private int age;

  public Student(int age, String name, String email) {
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
