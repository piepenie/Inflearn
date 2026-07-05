package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Day8 {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();

        Optional<Student> studentOptional = studentService.getStudentById(id);

        if (studentOptional.isEmpty()) {
            System.out.println("학생을 찾을 수 없습니다.");
            return;
        }

        Student student = studentOptional.get();
        System.out.println(student);

    }
}
