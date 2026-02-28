import org.example.commands.*;
import org.example.Context;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PushCommandTest {
    private Context context;
    private PushCommand pushCommand;

    @Before
    public void setUp() {
        context = new Context();
        pushCommand = new PushCommand();
    }

    @Test
    public void testPushNumber() throws Exception {
        String[] args = {"42.5"};
        pushCommand.execute(context, args);
        Assert.assertEquals(42.5, context.peekStack(), 0.0001);
    }

    @Test
    public void testPushVariable() throws Exception {
        context.setVariable("pi", 3.14159);
        String[] args = {"pi"};
        pushCommand.execute(context, args);
        Assert.assertEquals(3.14159, context.peekStack(), 0.0001);
    }

    @Test(expected = RuntimeException.class)
    public void testPushInvalidArgument() throws Exception {
        String[] args = {"unknown"};
        pushCommand.execute(context, args);
    }
}
