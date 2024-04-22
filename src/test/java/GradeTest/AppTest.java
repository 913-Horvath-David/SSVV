package GradeTest;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.*;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    private Service service;
    private StudentXMLRepository studentXMLRepository;
    private TemaXMLRepository temaXMLRepository;
    private NotaXMLRepository notaXMLRepository;

    private static final String STUDENTI_FILE = "studenti_test.xml";
    private static final String TEME_FILE = "teme_test.xml";
    private static final String NOTE_FILE = "note_test.xml";

    @BeforeEach
    protected void setUp() {
        createTestXMLFile(STUDENTI_FILE);
        createTestXMLFile(TEME_FILE);
        createTestXMLFile(NOTE_FILE);

        this.studentXMLRepository = new StudentXMLRepository(new StudentValidator(), STUDENTI_FILE);
        this.temaXMLRepository = new TemaXMLRepository(new TemaValidator(), TEME_FILE);
        this.notaXMLRepository = new NotaXMLRepository(new NotaValidator(), NOTE_FILE);
        this.service = new Service(this.studentXMLRepository, this.temaXMLRepository, this.notaXMLRepository);
    }

    @AfterEach
    protected void removeTestFiles() {
        new File(STUDENTI_FILE).delete();
        new File(TEME_FILE).delete();
        new File(NOTE_FILE).delete();
    }

    private void createTestXMLFile(String fileName) {
        File xml = new File(fileName);
        String xmlContent = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<inbox>\n</inbox>";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(xml))) {
            writer.write(xmlContent);
            writer.flush();
        } catch (IOException e) {
            System.err.println("Failed to create XML file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void addStudent_WithValidData_ShouldAddSuccessfully() {
        int initialCount = service.countStudents();
        assertEquals(1, service.saveStudent("500", "Alex", 933));
        assertEquals(initialCount + 1, service.countStudents());
    }

    @Test
    public void addTema_WithValidData_ShouldAddSuccessfully() {
        assertEquals(1, service.saveTema("510", "tema", 5, 1));
    }

    @Test
    public void addNota_WithValidData_ShouldAddSuccessfully() {
        int initialCount = service.countStudents();
        assertEquals(1, service.saveStudent("1", "Mihai", 936));
        assertEquals(initialCount + 1, service.countStudents());
        assertEquals(1, service.saveTema("2", "Tema SSVV", 5, 1));
        assertEquals(1, service.saveNota("1", "2", 10, 8, "Great job!"));
    }

    @Test
    public void addGrade_integration() {
        assertEquals(1, service.saveStudent("999", "Maria", 933));
        assertEquals(1, service.saveTema("222", "tema", 10, 1));
        assertEquals(1, service.saveNota("999", "222", 10, 8, "Great job!"));
    }
}
