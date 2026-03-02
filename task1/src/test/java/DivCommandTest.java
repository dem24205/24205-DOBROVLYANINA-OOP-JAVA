import org.example.commands.binary.*;
import org.example.Context;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DivCommandTest {
    private Context context;
    private DivCommand divCommand;

    @Before
    public void setUp() {
        context = new Context();
        divCommand = new DivCommand();
    }

    @Test
    public void testDivision() throws Exception {
        context.pushOnStack(10);
        context.pushOnStack(2);
        divCommand.execute(context, new String[]{});
        Assert.assertEquals(5.0, context.popFromStack(), 0.0001);
    }

    @Test(expected = RuntimeException.class)
    public void testDivisionByZero() throws Exception {
        context.pushOnStack(10);
        context.pushOnStack(0);
        divCommand.execute(context, new String[]{});
    }
}