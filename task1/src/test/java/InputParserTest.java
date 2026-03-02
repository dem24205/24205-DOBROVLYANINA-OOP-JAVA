import org.example.commands.*;
import org.example.commands.binary.*;
import org.example.InputParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InputParserTest {
    private InputParser parser;

    @Before
    public void setUp() {
        parser = new InputParser();
    }

    @Test
    public void testPushCommand() {
        String input = "PUSH 5";
        Assert.assertEquals("PUSH", parser.getCommandName(input));
        Assert.assertArrayEquals(new String[]{"5"}, parser.getAttributes(input));
    }

    @Test
    public void testDefineCommand() {
        String input = "DEFINE pi 3.14";
        Assert.assertEquals("DEFINE", parser.getCommandName(input));
        Assert.assertArrayEquals(new String[]{"pi", "3.14"}, parser.getAttributes(input));
    }

    @Test
    public void testPopCommand() {
        String input = "POP";
        Assert.assertEquals("POP", parser.getCommandName(input));
        Assert.assertArrayEquals(new String[]{}, parser.getAttributes(input));
    }

    @Test
    public void testSumCommand() {
        String input = "+";
        Assert.assertEquals("+", parser.getCommandName(input));
        Assert.assertArrayEquals(new String[]{}, parser.getAttributes(input));
    }

    @Test
    public void testComment() {
        String input = "# this is a comment";
        Assert.assertNull(parser.getCommandName(input));
    }

    @Test
    public void testEmptyString() {
        String input = "";
        Assert.assertNull(parser.getCommandName(input));
        Assert.assertArrayEquals(new String[]{}, parser.getAttributes(input));
    }

    @Test
    public void testNullInput() {
        Assert.assertNull(parser.getCommandName(null));
        Assert.assertArrayEquals(new String[]{}, parser.getAttributes(null));
    }

    @Test
    public void testCommandWithSpaces() {
        String input = "  PUSH   42   ";
        Assert.assertEquals("PUSH", parser.getCommandName(input));
        Assert.assertArrayEquals(new String[]{"42"}, parser.getAttributes(input));
    }
}
