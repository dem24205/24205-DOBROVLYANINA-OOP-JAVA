import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.InputValidator;

public class InputValidatorTest {
    @Test
    void validateValidInput() {
        InputValidator validator = new InputValidator(4);
        InputValidator.ValidationResult result = validator.validate("1234");
        assertTrue(result.isValid());
        assertNull(result.getErrorMessage());
    }

    @Test
    void validateNullInput() {
        InputValidator validator = new InputValidator(4);
        InputValidator.ValidationResult result = validator.validate(null);
        assertFalse(result.isValid());
        assertEquals("Error: empty input", result.getErrorMessage());
    }

    @Test
    void validateEmptyString() {
        InputValidator validator = new InputValidator(4);
        InputValidator.ValidationResult result = validator.validate("");
        assertFalse(result.isValid());
        assertEquals("Error: incorrect code length", result.getErrorMessage());
    }

    @Test
    void validateUnpermissibleSymbols() {
        InputValidator validator = new InputValidator(4);
        InputValidator.ValidationResult result = validator.validate("123a");
        assertFalse(result.isValid());
        assertEquals("Error: only digits are allowed", result.getErrorMessage());
    }

    @Test
    void validateUnUniqueSymbols() {
        InputValidator validator = new InputValidator(4);
        InputValidator.ValidationResult result = validator.validate("1233");
        assertFalse(result.isValid());
        assertEquals("Error: only unique digits are allowed", result.getErrorMessage());
    }
}
