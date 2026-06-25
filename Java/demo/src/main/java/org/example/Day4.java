package org.example;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Day4 {
    public static void main(String[] args) {
        List<Integer> scores = new ArrayList<>();

        scores.add(87);
        scores.add(92);
        scores.add(58);
        int total = 0;

        for (Integer score : scores) {
            total += score;
        }

        System.out.println("총점: " + total);
        System.out.println("평균: " + total / scores.size());

        Student student = new Student(1, "김하나", 14, 87);

//        System.out.println(student.getInfo());
//
//        student.changeScore(95);
//
//        System.out.println(student.getInfo());

        System.out.println(student.getProfile());
        System.out.println(student.isPassed());

    }
}
