import org.example.Context;
import org.example.commands.SqrtCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SqrtCommandTest {
    private Context context;
    private SqrtCommand sqrtCommand;

    @Before
    public void setUp() {
        context = new Context();
        sqrtCommand = new SqrtCommand();
    }

    @Test(expected = RuntimeException.class)
    public void sqrtNegativeNumberTest() throws Exception {
        context.pushOnStack(-1);
        sqrtCommand.execute(context, new String[]{});
    }

    @Test(expected = RuntimeException.class)
    public void sqrtEmptyStackTest() throws Exception {
        sqrtCommand.execute(context, new String[]{});
    }

    @Test
    public void testSqrtPositive() throws Exception {
        context.pushOnStack(16);
        sqrtCommand.execute(context, new String[]{});
        Assert.assertEquals(4.0, context.popFromStack(), 0.0001);
    }
}
