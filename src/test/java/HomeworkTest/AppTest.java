package HomeworkTest;

import domain.Tema;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.TemaRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.TemaValidator;
import validation.Validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AppTest {
    private static Service service;
    private static TemaXMLRepository temaRepository;

    @BeforeAll
    public static void setup(){
        Validator<Tema> temaValidator = new TemaValidator();
        temaRepository = new TemaXMLRepository(temaValidator, "test_teme.xml");
        service = new Service(null, temaRepository, null);
    }

    @Test
    public void addTema_WithValidData_ShouldAddSuccessfully() {
        assertEquals(0, service.saveTema("100", "abc", 5, 1));
    }

    @Test
    public void addTema_WithNullId_ShouldFail() {
        assertEquals(1, service.saveTema(null, "def", 4, 1));
    }
}
