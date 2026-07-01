import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    public void run() {
        Scanner scanner = new Scanner(System.in);

        Classroom classroom = new Classroom();
        classroom.addStudent(new Student(1, "김하나", 87));
        classroom.addStudent(new Student(2, "이도윤", 92));
        classroom.addStudent(new Student(3, "박서준", 58));

        System.out.println("학생 조회 프로그램이 동작중입니다.");

        System.out.println("학생 목록");
        System.out.println(classroom.getStudents());

        System.out.println("수정할 학생 ID를 입력하세요.");
        int inputId = scanner.nextInt();

        Optional<Student> studentOptional = classroom.findById(inputId);
        if(studentOptional.isEmpty()) {
            System.out.println("해당 ID를 가진 학생이 존재하지않습니다.");
            scanner.close();
            return;
        }

        Student findStudent = studentOptional.get();
        System.out.println("검색된 학생 : " + findStudent.getName());

        System.out.println("수정할 새 점수를 입력하세요.");
        int updateScore = scanner.nextInt();

        try {
            findStudent.updateScore(updateScore);
            System.out.println("점수가 수정되었습니다 : " + findStudent);
        } catch (IllegalArgumentException e) {
            System.out.println("점수가 잘못입력되었습니다. 종료합니다.");
            scanner.close();
            return;
        }

        System.out.println("합격자");
        List<Student> passStudents = classroom.getPassedStudents();
        System.out.println(passStudents);

        System.out.println("최고득점 학생");
        Optional<Student> topStudent = classroom.findTopStudent();
        System.out.println(topStudent);


        scanner.close();

    }

    public static void main(String[] args) {
        Main app = new Main();
        app.run();
    }

}
