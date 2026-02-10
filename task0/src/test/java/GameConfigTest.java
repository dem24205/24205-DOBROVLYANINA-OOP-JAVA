import org.example.GameConfig;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameConfigTest {
    @Test
    void testDefaultConfig() {
        GameConfig config = GameConfig.defaultConfig();
        assertEquals(4, config.getCodeLength());
        assertEquals(5, config.getAttemptsNum());
    }

    @Test
    void testFromArgsWithValidArguments() {
        String[] args = {"6", "10"};
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
        String[] args1 = {"5"};
        String[] args2 = {"5", "10", "15"};
        assertThrows(IllegalArgumentException.class, () -> GameConfig.fromArgs(args1));
        assertThrows(IllegalArgumentException.class, () -> GameConfig.fromArgs(args2));
    }

    @Test
    void testFromArgsWithNonDigitArguments() {
        String[] args1 = {"abc", "10"};
        String[] args2 = {"5", "xyz"};
        String[] args3 = {"", ""};
        assertThrows(IllegalArgumentException.class, () -> GameConfig.fromArgs(args1));
        assertThrows(IllegalArgumentException.class, () -> GameConfig.fromArgs(args2));
        assertThrows(IllegalArgumentException.class, () -> GameConfig.fromArgs(args3));
    }

    @Test
    void testFromArgsWithInvalidCodeLength() {
        String[] args1 = {"1", "5"};
        String[] args2 = {"11", "5"};
        assertThrows(IllegalArgumentException.class, () -> GameConfig.fromArgs(args1));
        assertThrows(IllegalArgumentException.class, () -> GameConfig.fromArgs(args2));
    }

    @Test
    void testFromArgsWithInvalidAttemptsNumber() {
        String[] args = {"4", "0"};  // меньше MIN_ATTEMPTS
        assertThrows(IllegalArgumentException.class, () -> GameConfig.fromArgs(args));
    }
}
