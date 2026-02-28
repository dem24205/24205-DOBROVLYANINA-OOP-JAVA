import org.example.commands.binary.*;
import org.example.Context;
import org.junit.Before;
import org.junit.Test;

public class BinaryOperationCommandTest {
    private Context context;
    private BinaryOperationCommand command;

    @Before
    public void setUp() {
        context = new Context();
        command = new BinaryOperationCommand();
    }

    @Test(expected = RuntimeException.class)
    public void testNotEnoughElements() throws Exception {
        context.pushOnStack(5);
        command.execute(context, new String[]{});
    }

    @Test(expected = RuntimeException.class)
    public void testEmptyStack() throws Exception {
        command.execute(context, new String[]{});
    }
}