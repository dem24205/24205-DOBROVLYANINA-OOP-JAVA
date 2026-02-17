import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.GameEngine;
import org.example.Guess;

public class GameEngineTest {
    @Test
    void allBullsTest() {
        Guess g = GameEngine.calculateBullsAndCows("12345", "12345");
        assertEquals(5, g.getBulls());
        assertEquals(0, g.getCows());
    }

    @Test
    void allCowsTest() {
        Guess g = GameEngine.calculateBullsAndCows("12345", "54321");
        assertEquals(1, g.getBulls()); // только середина (3) на месте
        assertEquals(4, g.getCows());
    }

    @Test
    void noMatchesTest() {
        Guess g = GameEngine.calculateBullsAndCows("12345", "67890");
        assertEquals(0, g.getBulls());
        assertEquals(0, g.getCows());
    }
}

