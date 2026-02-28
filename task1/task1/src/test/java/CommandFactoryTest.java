import org.example.commands.*;
import org.example.commands.binary.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.example.Command;
import org.example.CommandFactory;

import java.util.NoSuchElementException;

public class CommandFactoryTest {
    private CommandFactory commandFactory;

    @Before
    public void setUp() {
        commandFactory = new CommandFactory();
    }

    @Test
    public void testPushCommand() {
        Command command = commandFactory.createCommand("PUSH");
        Assert.assertEquals(PushCommand.class, command.getClass());
    }

    @Test
    public void testPopCommand() {
        Command command = commandFactory.createCommand("POP");
        Assert.assertEquals(PopCommand.class, command.getClass());
    }

    @Test
    public void testDefineCommand() {
        Command command = commandFactory.createCommand("DEFINE");
        Assert.assertEquals(DefineCommand.class, command.getClass());
    }

    @Test
    public void testSqrtCommand() {
        Command command = commandFactory.createCommand("SQRT");
        Assert.assertEquals(SqrtCommand.class, command.getClass());
    }

    @Test
    public void testPrintCommand() {
        Command command = commandFactory.createCommand("PRINT");
        Assert.assertEquals(PrintCommand.class, command.getClass());
    }

    @Test
    public void testDivCommand() {
        Command command = commandFactory.createCommand("/");
        Assert.assertEquals(DivCommand.class, command.getClass());
    }

    @Test
    public void testMulCommand() {
        Command command = commandFactory.createCommand("*");
        Assert.assertEquals(MulCommand.class, command.getClass());
    }

    @Test
    public void testSubCommand() {
        Command command = commandFactory.createCommand("-");
        Assert.assertEquals(SubCommand.class, command.getClass());
    }

    @Test
    public void testSumCommand() {
        Command command = commandFactory.createCommand("+");
        Assert.assertEquals(SumCommand.class, command.getClass());
    }

    @Test (expected = NoSuchElementException.class)
    public void testUnknownCommand() {
        commandFactory.createCommand("UNKNOWN");
    }
}
