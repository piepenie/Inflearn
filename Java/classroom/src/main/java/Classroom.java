import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Classroom {
    private final List<Student> students;

    public Classroom() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public List<Student> getStudents() {
        return new ArrayList<>(this.students);
    }

    public Optional<Student> findById(int id) {
        return students.stream()
                .filter(student -> student.getId() == id)
                .findFirst();
    }

    public List<Student> getPassedStudents() {
        return students.stream()
                .filter(student -> student.getPassStatus() == PassStatus.PASSED)
                .collect(Collectors.toList());
    }

    public Optional<Student> findTopStudent() {
        return students.stream()
                .max(Comparator.comparingInt(Student::getScore));
    }

    public List<Student> searchByName(String keyword) {
        return students.stream()
                .filter(student -> student.getName().contains(keyword))
                .collect(Collectors.toList());
    }

}
