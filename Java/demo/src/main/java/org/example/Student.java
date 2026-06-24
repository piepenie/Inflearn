package org.example;

public class Student {
    private int id;
    private String name;
    private int age;
    private int score;

    public Student(int id, String name, int age, int score) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        if (score < 0 || score > 100) {
            return;
        }

        this.score = score;
    }

    @Override
    public String toString() {
        return id + " / " + name + " / " + age + "세 / " + score + "점";
    }

    public void printInfo() {
        System.out.println(name + " / " + age + "세 / " + score + "점");
    }

    boolean isPassed() {
        return score >= 60;
    }
}
