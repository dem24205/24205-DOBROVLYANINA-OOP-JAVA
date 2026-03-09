import org.example.Context;
import org.example.commands.binary.SubCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SubCommandTest {
    private Context context;
    private SubCommand subCommand;

    @Before
    public void setUp() {
        context = new Context();
        subCommand = new SubCommand();
    }

    @Test
    public void testSubtraction() throws Exception {
        context.pushOnStack(10);
        context.pushOnStack(3);
        subCommand.execute(context, new String[]{});
        Assert.assertEquals(7.0, context.popFromStack(), 0.0001);
    }
}