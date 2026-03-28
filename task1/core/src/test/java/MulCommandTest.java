import org.example.Context;
import org.example.commands.binary.MulCommand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MulCommandTest {

    @Mock
    private Context mockContext;

    @InjectMocks
    private MulCommand mulCommand;

    @Test
    public void testMultiplication() throws Exception {
        when(mockContext.popFromStack()).thenReturn(4.0, 3.0);
        mulCommand.execute(mockContext, new String[]{});
        verify(mockContext, times(2)).popFromStack();
        verify(mockContext).pushOnStack(12.0);
    }

    @Test
    public void testMultiplicationWithZero() throws Exception {
        when(mockContext.popFromStack()).thenReturn(0.0, 5.0);
        mulCommand.execute(mockContext, new String[]{});
        verify(mockContext, times(2)).popFromStack();
        verify(mockContext).pushOnStack(0.0);
    }
}