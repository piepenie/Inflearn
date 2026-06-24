package org.example;

public class Car {
    String name;
    String color;
    String number;

    Car(String name, String color, String number) {
        this.name = name;
        this.color = color;
        this.number = number;
    }

    @Override
    public String toString() {
        return "자동차 정보 [모델명=" + name + ", 색상=" + color + ", 차량번호=" + number + "]";
    }
}
