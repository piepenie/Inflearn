package org.example;

public class Day2 {
    public static void main(String[] args) {
//        Bread bread = new Bread();
//        bread.name = "마들렌";
//
//        System.out.println(bread.name);

//        Car car1 = new Car("포르쉐", "레드", "16515");
//        System.out.println(car1);

        Cat cat = new Cat("삼색이", 15);
        cat.meow();
        cat.walk();
        String poo = cat.poo();
        System.out.println(poo);

        Cat cat2 = new Cat("길막이", 15);
        cat2.meow();
        cat2.walk();
        String poo2 = cat2.poo();
        System.out.println(poo2);

        Cat cat3 = new Cat("무", 13);
        cat3.meow();
        cat3.walk();
        String poo3 = cat3.poo();
        System.out.println(poo3);

    }
}