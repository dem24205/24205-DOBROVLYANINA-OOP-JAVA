import org.example.GameConfig;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameConfigTest {
    @Test
    void testFromArgsWithValidArguments() {
        String[] args = {"-d=6", "-a=10"};
        GameConfig config = GameConfig.fromArgs(args);
        assertEquals(6, config.getCodeLength());
        assertEquals(10, config.getAttemptsNum());
    }

    @Test
    void testFromArgsWithNoArguments() {
        String[] args = {};
        GameConfig config = GameConfig.fromArgs(args);
        assertEquals(4, config.getCodeLength());
        assertEquals(5, config.getAttemptsNum());
    }

    @Test
    void testFromArgsWithInvalidNumberOfArguments() {
        String[] args1 = {"-d=5"};
        String[] args2 = {"-d=5", "-a=10", "-a=15"};
        assertThrows(IllegalArgumentException.class, () -> GameConfig.fromArgs(args1));
        assertThrows(IllegalArgumentException.class, () -> GameConfig.fromArgs(args2));
    }

    @Test
    void testFromArgsWithNonDigitArguments() {
        String[] args1 = {"-d=abc", "-a=10"};
        String[] args2 = {"-d=5", "-a=xyz"};
        assertThrows(IllegalArgumentException.class, () -> GameConfig.fromArgs(args1));
        assertThrows(IllegalArgumentException.class, () -> GameConfig.fromArgs(args2));
    }

    @Test
    void testFromArgsWithInvalidCodeLength() {
        String[] args2 = {"-d=11", "-a=5"};
        String[] args3 = {"-d=5", "-a=0"};
        assertThrows(IllegalArgumentException.class, () -> GameConfig.fromArgs(args2));
        assertThrows(IllegalArgumentException.class, () -> GameConfig.fromArgs(args3));
    }
}
