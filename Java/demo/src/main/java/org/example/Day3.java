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
        List<Student> students = new ArrayList<>();

        students.add(new Student(1, "김하나", 20, 87));
        students.add(new Student(2, "이도윤", 21, 92));
        students.add(new Student(3, "박서준", 19, 58));
        students.add(new Student(4, "김도윤", 22, 76));

        System.out.println("전체 학생");
        printAll(students);

        Student found = findById(students, 2);
        if (found != null) {
            System.out.println("id 조회: " + found);
        }

        System.out.println("이름 검색");
        searchByName(students, "김");

        System.out.println("합격자");
        printPassedStudents(students);

        updateScore(students, 1, 95);

        System.out.println("수정 후");
        Student updated = findById(students, 1);
        if (updated != null) {
            System.out.println(updated);
        }

        deleteById(students, 3);

        System.out.println("삭제 후");
        printAll(students);

        System.out.println("총 학생 수: " + students.size());
    }

    public static void printAll(List<Student> students) {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public static Student findById(List<Student> students, int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }

        return null;
    }

    public static void searchByName(List<Student> students, String keyword) {
        for (Student student : students) {
            if (student.getName().contains(keyword)) {
                System.out.println(student);
            }
        }
    }

    public static void printPassedStudents(List<Student> students) {
        for (Student student : students) {
            if (student.isPassed()) {
                System.out.println(student);
            }
        }
    }

    public static void updateScore(List<Student> students, int id, int score) {
        Student student = findById(students, id);

        if (student != null) {
            student.setScore(score);
        }
    }

    public static void deleteById(List<Student> students, int id) {
        Student student = findById(students, id);

        if (student != null) {
            students.remove(student);
        }
    }
}

