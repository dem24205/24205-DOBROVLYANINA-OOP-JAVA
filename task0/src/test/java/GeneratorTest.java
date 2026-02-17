import org.example.Generator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GeneratorTest {
    @Test
    void validLengthTest() {
        Generator gen = new Generator(5);
        String code = gen.generate();
        assertEquals(5, code.length());
    }
}
