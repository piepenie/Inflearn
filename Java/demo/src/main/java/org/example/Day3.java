package org.example;
import java.util.ArrayList;
import java.util.List;

public class Day3 {
    public static void main(String[] args) {
//        Student student = new Student("김하나", 20, 87);
//
//        System.out.println(student.getName());
//        System.out.println(student.getScore());
//
//        student.setScore(95);
//        System.out.println(student.getScore());
//
//        student.setScore(500);
//        System.out.println(student.getScore());

        // Long <<= wrapper class
        // long <<= primitive type

//        List<Student> students = new ArrayList<>();
//
//        students.add(new Student(1, "김하나", 20, 87));
//        students.add(new Student(2, "이도윤", 21, 92));
//        students.add(new Student(3, "박서준", 19, 58));
//
//        for (Student student : students) {
//            System.out.println(student);
//        }
        public static Student findById(List<Student> students, int id){
            for (Student student : students) {
                if (student.getId() == id) {
                    return student;
                }
            }

            return null;
        }

    }
}

