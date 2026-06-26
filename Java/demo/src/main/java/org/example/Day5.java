package org.example;

public class Day5 {

    //생성자 <= 생성 하는거 제한 시켜줌
    //fnial private << 숨길께요
    //getter setter


    // 다형성 활용
    public static void main(String[] args) {

        // 다형성 활용
        Animal animal = new Cat();
        animal.exist();
        animal.makeSound();
    }
}