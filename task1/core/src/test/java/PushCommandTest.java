import org.example.Context;
import org.example.commands.PushCommand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.NoSuchElementException;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PushCommandTest {

    @Mock
    private Context mockContext;

    @InjectMocks
    private PushCommand pushCommand;

    @Test
    public void testPushNumber() throws Exception {
        String[] args = {"42.5"};
        when(mockContext.getVariable("42.5")).thenThrow(new NoSuchElementException());
        pushCommand.execute(mockContext, args);
        verify(mockContext).getVariable("42.5");
        verify(mockContext).pushOnStack(42.5);
    }

    @Test
    public void testPushVariable() throws Exception {
        String[] args = {"pi"};
        when(mockContext.getVariable("pi")).thenReturn(3.14159);
        pushCommand.execute(mockContext, args);
        verify(mockContext).getVariable("pi");
        verify(mockContext).pushOnStack(3.14159);
    }

    @Test(expected = RuntimeException.class)
    public void testPushInvalidArgument() throws Exception {
        String[] args = {"unknown"};
        when(mockContext.getVariable("unknown")).thenThrow(new NoSuchElementException());
        pushCommand.execute(mockContext, args);
    }

    @Test
    public void testPushNegativeNumber() throws Exception {
        String[] args = {"-10.5"};
        when(mockContext.getVariable("-10.5")).thenThrow(new NoSuchElementException());
        pushCommand.execute(mockContext, args);
        verify(mockContext).getVariable("-10.5");
        verify(mockContext).pushOnStack(-10.5);
    }
}