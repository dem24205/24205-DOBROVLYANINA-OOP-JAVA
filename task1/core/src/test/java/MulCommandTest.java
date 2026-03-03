import org.example.Context;
import org.example.commands.binary.MulCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MulCommandTest {
    private Context context;
    private MulCommand mulCommand;

    @Before
    public void setUp() {
        context = new Context();
        mulCommand = new MulCommand();
    }

    @Test
    public void testMultiplication() throws Exception {
        context.pushOnStack(3);
        context.pushOnStack(4);
        mulCommand.execute(context, new String[]{});
        Assert.assertEquals(12.0, context.popFromStack(), 0.0001);
    }

    @Test
    public void testMultiplicationWithZero() throws Exception {
        context.pushOnStack(5);
        context.pushOnStack(0);
        mulCommand.execute(context, new String[]{});
        Assert.assertEquals(0.0, context.popFromStack(), 0.0001);
    }
}