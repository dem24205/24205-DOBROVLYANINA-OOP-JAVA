import org.example.commands.*;
import org.example.Context;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DefineCommandTest {
    private Context context;
    private DefineCommand defineCommand;

    @Before
    public void setUp() {
        context = new Context();
        defineCommand = new DefineCommand();
    }

    @Test
    public void testDefineVariable() throws Exception {
        defineCommand.execute(context, new String[]{"pi", "3.14159"});
        Assert.assertEquals(3.14159, context.getVariable("pi"), 0.0001);
    }

    @Test(expected = RuntimeException.class)
    public void testDefineMissingArgument() throws Exception {
        defineCommand.execute(context, new String[]{"pi"});
    }

    @Test(expected = RuntimeException.class)
    public void testDefineInvalidVariableName() throws Exception {
        defineCommand.execute(context, new String[]{"123pi", "3.14"});
    }

    @Test(expected = RuntimeException.class)
    public void testDefineInvalidNumber() throws Exception {
        defineCommand.execute(context, new String[]{"pi", "three point fourteen"});
    }
}
