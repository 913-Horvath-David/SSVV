package HomeworkTest;

import domain.Tema;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.TemaXMLRepository;
import service.Service;
import validation.TemaValidator;
import validation.Validator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    private static Service service;

    @BeforeAll
    public static void setup(){
        Validator<Tema> temaValidator = new TemaValidator();
        TemaXMLRepository temaRepository = new TemaXMLRepository(temaValidator, "test_teme.xml");
        service = new Service(null, temaRepository, null);
    }

    @Test
    public void addTema_WithValidData_ShouldAddSuccessfully() {
        assertEquals(1, service.saveTema("110", "tema", 5, 1));
    }

    @Test
    public void addTema_WithNullId_ShouldFail() {
        assertEquals(0, service.saveTema(null, "tema1", 4, 1));
    }

    @Test
    public void addTema_WithEmptyId_ShouldFail() {
        assertEquals(0, service.saveTema("", "tema1", 4, 1));
    }

    @Test
    public void addTema_WithNullDescription_ShouldFail() {
        assertEquals(0, service.saveTema("101", null, 3, 1));
    }

    @Test
    public void addTema_WithEmptyDescription_ShouldFail() {
        assertEquals(0, service.saveTema("101", "", 7, 2));
    }

    @Test
    public void addTema_WithTooSmallDeadline_ShouldFail() {
        assertEquals(0, service.saveTema("102", "tema2", 0, 1));
    }

    @Test
    public void addTema_WithTooLargeDeadline_ShouldFail() {
        assertEquals(0, service.saveTema("102", "tema2", 15, 1));
    }

    @Test
    public void addTema_WithTooSmallStartline_ShouldFail() {
        assertEquals(0, service.saveTema("103", "tema3", 1, 0));
    }

    @Test
    public void addTema_WithTooLargeStartline_ShouldFail() {
        assertEquals(0, service.saveTema("103", "tema3", 11, 16));
    }

    @Test
    public void addTema_WithInvalidDates_ShouldFail() {
        assertEquals(0, service.saveTema("104", "tema4", 2, 6));
    }

    @Test
    public void addTema_WithDuplicateId_ShouldFail() {
        assertEquals(1, service.saveTema("1", "test", 12, 10));
        assertEquals(0, service.saveTema("1", "test", 12, 10));
    }

    @Test
    public void addTema_WithNegativeId_ShouldFail() {
        assertEquals(0, service.saveTema("-1", "test", 2, 1));
    }
}
