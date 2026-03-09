import org.example.Context;
import org.example.commands.binary.BinaryOperationCommand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.NoSuchElementException;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BinaryOperationCommandTest {

    @Mock
    private Context mockContext;

    @InjectMocks
    private BinaryOperationCommand command;

    @Test
    public void testNotEnoughElements() throws Exception {
        when(mockContext.popFromStack()).thenReturn(5.0).thenThrow(new NoSuchElementException());
        try {
            command.execute(mockContext, new String[]{});
        } catch (RuntimeException e) {
            verify(mockContext, times(2)).popFromStack();
            verify(mockContext).pushOnStack(5.0);
        }
    }

    @Test
    public void testEmptyStack() throws Exception {
        when(mockContext.popFromStack()).thenThrow(new NoSuchElementException());
        try {
            command.execute(mockContext, new String[]{});
        } catch (RuntimeException e) {
            verify(mockContext, times(1)).popFromStack();
            verify(mockContext, never()).pushOnStack(anyDouble());
        }
    }
}