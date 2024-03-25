package validation;
import domain.Student;

public class StudentValidator implements Validator<Student> {
    public void validate(Student student) throws ValidationException {
        // Validate student ID
        String studentID = student.getID();

        if (studentID == null || studentID.trim().isEmpty()) {
            throw new ValidationException("ID invalid! ID nu poate sa fie nula sau vida!\n");
        }

        try {
            int integerID = Integer.parseInt(studentID.trim());
            if (integerID <= 0) {
                throw new ValidationException("ID invalid! ID trebuie sa fie numar intreg pozitiv!\n");
            }
        } catch (NumberFormatException nfe) {
            throw new ValidationException("ID invalid! ID trebuie sa fie numar intreg!\n");
        }

        // Validate student name
        if (student.getNume() == null || student.getNume().trim().isEmpty()) {
            throw new ValidationException("Nume invalid! Nume nu poate fi nul sau vid!\n");
        }

        // Validate student group
        int grupa = student.getGrupa();
        if (grupa <= 110 || grupa >= 938) {
            throw new ValidationException("Grupa invalida! Grupa trebuie sa fie numar intreg intre 111 si 937.\n");
        }
    }
}
