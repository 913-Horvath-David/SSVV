package validation;
import domain.Tema;

public class TemaValidator implements Validator<Tema> {
    public void validate(Tema tema) throws ValidationException {
        String temaID = tema.getID();

        if (temaID == null || temaID.isEmpty()) {
            throw new ValidationException("ID invalid! \n");
        }

        try {
            int integerID = Integer.parseInt(temaID.trim());
            if (integerID < 0) {
                throw new ValidationException("ID invalid! ID trebuie sa fie numar intreg pozitiv!\n");
            }
        } catch (NumberFormatException nfe) {
            throw new ValidationException("ID invalid! ID trebuie sa fie numar intreg!\n");
        }

        if (tema.getDescriere() == null || tema.getDescriere().isEmpty()) {
            throw new ValidationException("Descriere invalida! \n");
        }

        if (tema.getDeadline() < 1 || tema.getDeadline() > 14) {
            throw new ValidationException("Deadline invalid! \n");
        }

        if (tema.getStartline() < 1 || tema.getStartline() > 14) {
            throw new ValidationException("Data de primire invalida! \n");
        }

        if (tema.getStartline() > tema.getDeadline()) {
            throw new ValidationException("Data de primire nu poate fi dupa deadline! \n");
        }
    }
}
