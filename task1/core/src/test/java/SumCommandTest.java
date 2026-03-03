import org.example.Context;
import org.example.commands.binary.SumCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SumCommandTest {
    private Context context;
    private SumCommand sumCommand;

    @Before
    public void setUp() {
        context = new Context();
        sumCommand = new SumCommand();
    }

    @Test
    public void testSumPositive() throws Exception {
        context.pushOnStack(3);
        context.pushOnStack(4);
        sumCommand.execute(context, new String[]{});
        Assert.assertEquals(7.0, context.popFromStack(), 0.0001);
    }

    @Test
    public void testSumWithNegative() throws Exception {
        context.pushOnStack(-5);
        context.pushOnStack(3);
        sumCommand.execute(context, new String[]{});
        Assert.assertEquals(-2.0, context.popFromStack(), 0.0001);
    }
}