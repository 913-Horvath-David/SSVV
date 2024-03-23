package StudentTest;

import domain.Student;
import org.junit.jupiter.api.Test;
import repository.StudentRepository;
import validation.StudentValidator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    @Test
    public void testAddStudent1() {
        // This should pass
        StudentRepository studentRepository = new StudentRepository(new StudentValidator());
        Student student = new Student("1", "Andrei", 937);
        studentRepository.save(student);
        assertEquals(studentRepository.findOne("1"), student);
    }

    @Test
    public void testAddStudent2() {
        // This should fail
        StudentRepository studentRepository = new StudentRepository(new StudentValidator());
        Student student = new Student("1", "Andrei", 936);
        studentRepository.save(student);
        assertEquals(studentRepository.findOne("1"), student);
    }
}
