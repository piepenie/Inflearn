package org.example;

public class Student extends Person {
    private int id;
//    private final String name;
    private int age;
    private int score;



    public Student(int id, String name, int age, int score) {
//        this.id = id;
//        this.name = name;
        super(name, age);
        this.age = age;
        this.score = score;
    }

    public int getId() {
        return id;
    }

//    public String getName() {
//        return name;
//    }

    public int getScore() {
        return score;
    }

    public void changeScore(int score) {
        this.score = score;
    }

//    public String getInfo() {
//        return id + " / " + name + " / " + score + "점";
//    }

    public void setScore(int score) {
        if (score < 0 || score > 100) {
            return;
        }

        this.score = score;
    }

//    @Override
//    public String toString() {
//        return id + " / " + name + " / " + age + "세 / " + score + "점";
//    }
//
//    public void printInfo() {
//        System.out.println(name + " / " + age + "세 / " + score + "점");
//    }

    boolean isPassed() {
        return score >= 60;
    }
}
