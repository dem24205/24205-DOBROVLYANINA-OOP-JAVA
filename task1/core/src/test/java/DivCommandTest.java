import org.example.Context;
import org.example.commands.binary.DivCommand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DivCommandTest {

    @Mock
    private Context mockContext;

    @InjectMocks
    private DivCommand divCommand;

    @Test
    public void testDivision() throws Exception {
        when(mockContext.popFromStack()).thenReturn(2.0, 10.0);
        divCommand.execute(mockContext, new String[]{});
        verify(mockContext, times(2)).popFromStack();
        verify(mockContext).pushOnStack(5.0);
    }

    @Test
    public void testDivisionByZero() throws Exception {
        when(mockContext.popFromStack()).thenReturn(0.0, 10.0);
        try {
            divCommand.execute(mockContext, new String[]{});
        } catch (RuntimeException e) {
            assertEquals("Division by zero", e.getMessage());
            verify(mockContext, times(2)).popFromStack();
            verify(mockContext).pushOnStack(10.0);
            verify(mockContext).pushOnStack(0.0);
        }
    }
}
