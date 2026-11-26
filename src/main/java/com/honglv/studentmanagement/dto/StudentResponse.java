package com.honglv.studentmanagement.dto;
import com.honglv.studentmanagement.model.Student;

public record StudentResponse(Long id, String name, String email, int age) {
    public static StudentResponse from(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getStudentAge()
        );
    }
}