package StudentTest;

import domain.Student;
import org.junit.jupiter.api.*;
import repository.StudentXMLRepository;
import validation.*;
import service.Service;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    private static Service studentService;
    private static StudentXMLRepository studentXMLRepository;

    @BeforeAll
    public static void setup(){
        Validator<Student> studentValidator = new StudentValidator();
        studentXMLRepository = new StudentXMLRepository(studentValidator, "test_studenti.xml");
        studentService = new Service(studentXMLRepository, null, null);
    }

    @Test
    public void addStudent_WithValidData_ShouldAddSuccessfully() {
        int initialCount = studentService.countStudents();
        assertEquals(1, studentService.saveStudent("100", "Alex", 933));
        assertEquals(initialCount + 1, studentService.countStudents());
    }

    @Test
    public void addStudent_WithNullId_ShouldFail() {
        int initialCount = studentService.countStudents();
        assertEquals(0, studentService.saveStudent(null, "Maria", 931));
        assertEquals(initialCount, studentService.countStudents());
    }

    @Test
    public void addStudent_WithEmptyId_ShouldFail() {
        int initialCount = studentService.countStudents();
        assertEquals(0, studentService.saveStudent("", "Andrei", 937));
        assertEquals(initialCount, studentService.countStudents());
    }

    @Test
    public void addStudent_WithNegativeId_ShouldFail() {
        int initialCount = studentService.countStudents();
        assertEquals(0, studentService.saveStudent("-1", "Andrei", 937));
        assertEquals(initialCount, studentService.countStudents());
    }

    @Test
    public void addStudent_WithNonNumericId_ShouldFail() {
        int initialCount = studentService.countStudents();
        assertEquals(0, studentService.saveStudent("abc", "Andrei", 937));
        assertEquals(initialCount, studentService.countStudents());
    }

    @Test
    public void addStudent_WithNullName_ShouldFail() {
        int initialCount = studentService.countStudents();
        assertEquals(0, studentService.saveStudent("101", null, 937));
        assertEquals(initialCount, studentService.countStudents());
    }

    @Test
    public void addStudent_WithEmptyName_ShouldFail() {
        int initialCount = studentService.countStudents();
        assertEquals(0, studentService.saveStudent("102", "", 937));
        assertEquals(initialCount, studentService.countStudents());
    }

    @Test
    public void addStudent_WithGroupNumberTooLow_ShouldFail() {
        int initialCount = studentService.countStudents();
        assertEquals(0, studentService.saveStudent("103", "Andrei", 110));
        assertEquals(initialCount, studentService.countStudents());
    }

    @Test
    public void addStudent_WithGroupNumberTooHigh_ShouldFail() {
        int initialCount = studentService.countStudents();
        assertEquals(0, studentService.saveStudent("104", "Maria", 938));
        assertEquals(initialCount, studentService.countStudents());
    }

    @Test
    public void addStudent_WithDuplicateId_ShouldFail() {
        studentService.saveStudent("105", "Andrei", 937);
        int initialCount = studentService.countStudents();
        assertEquals(0, studentService.saveStudent("105", "Mihai", 937));
        assertEquals(initialCount, studentService.countStudents());
    }

    @Test
    public void testAddStudentIC1() {
        // This should pass
        Student student = new Student("106", "Alex", 937);
        studentXMLRepository.save(student);
        assertEquals(studentXMLRepository.findOne("106"), student);
    }

    @Test
    public void testAddStudentIC2() {
        // This should fail
        Student student = new Student("108", "Mihai", 832);
        studentXMLRepository.save(student);
        assertNull(studentXMLRepository.findOne("11"));
    }
}
