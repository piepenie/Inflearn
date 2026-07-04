package org.example;

class ParentC {
    void printMessage() {
        System.out.println("나는 부모 클래스야.");
    }
    String run(){
        return "달린다.";
    }
}

class ChildC extends ParentC {
    void printMessage() {
        System.out.println("나는 자식 클래스야.");
    }
    @Override
    String run() {
        return super.run();
    }
}

public class Day7 {
    public static void main(String[] args) {
        ParentC parentC = new ParentC();
        ChildC childC = new ChildC();

        parentC.printMessage();
        childC.printMessage();

        System.out.println(childC.run());

    }
}
