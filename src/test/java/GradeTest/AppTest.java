package GradeTest;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AppTest {
    private static Service studentService;
    private static Service temaService;

    private static Service notaService;

    @BeforeAll
    public static void setup(){
        Validator<Student> studentValidator = new StudentValidator();
        StudentXMLRepository studentRepository = new StudentXMLRepository(studentValidator, "test_studenti.xml");
        studentService = new Service(studentRepository, null, null);

        Validator<Tema> temaValidator = new TemaValidator();
        TemaXMLRepository temaRepository = new TemaXMLRepository(temaValidator, "test_teme.xml");
        temaService = new Service(null, temaRepository, null);

        Validator<Nota> notaValidator = new NotaValidator();
        NotaXMLRepository notaRepository = new NotaXMLRepository(notaValidator, "test_note.xml");
        notaService = new Service(studentRepository, temaRepository, notaRepository);
    }

    @Test
    public void addStudent_WithValidData_ShouldAddSuccessfully() {
        int initialCount = studentService.countStudents();
        assertEquals(1, studentService.saveStudent("500", "Alex", 933));
        assertEquals(initialCount + 1, studentService.countStudents());
    }

    @Test
    public void addTema_WithValidData_ShouldAddSuccessfully() {
        assertEquals(1, temaService.saveTema("510", "tema", 5, 1));
    }

    @AfterAll
    public static void addNota_WithValidData_ShouldAddSuccessfully() {
        assertEquals(1, notaService.saveNota("500", "510", 7, 3, "Good"));
    }

    @Test
    public void addGrade_integration() {
        assertEquals(1, studentService.saveStudent("999", "Maria", 933));
        assertEquals(1, temaService.saveTema("222", "tema", 10, 1));
        assertEquals(1, notaService.saveNota("999", "222", 10, 8, "Great job!"));
    }
}
