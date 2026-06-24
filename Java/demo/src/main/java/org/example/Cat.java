package org.example;

public class Cat {
    String name;
    int age;

    Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void meow() {
        System.out.println("냐옹");
    }

    public void walk() {
        System.out.println(name + " 는/이 걷는다");
    }

    public String poo() {
        return "똥";
    }

}
