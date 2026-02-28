import org.example.commands.*;
import org.example.Context;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PopCommandTest {
    private Context context;
    private PopCommand popCommand;

    @Before
    public void setUp() {
        context = new Context();
        popCommand = new PopCommand();
    }

    @Test
    public void testPopWithoutVariable() throws Exception {
        context.pushOnStack(42.5);
        popCommand.execute(context, new String[]{});
        Assert.assertTrue(context.isStackEmpty());
    }

    @Test
    public void testPopWithVariable() throws Exception {
        context.pushOnStack(3.14);
        popCommand.execute(context, new String[]{"pi"});
        Assert.assertTrue(context.isStackEmpty());
        Assert.assertEquals(3.14, context.getVariable("pi"), 0.0001);
    }

    @Test(expected = RuntimeException.class)
    public void testPopEmptyStack() throws Exception {
        popCommand.execute(context, new String[]{});
    }
}
