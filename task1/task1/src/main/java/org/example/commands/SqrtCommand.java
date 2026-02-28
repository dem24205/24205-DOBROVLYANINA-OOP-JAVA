package org.example.commands;

import org.example.Command;
import org.example.Context;
import org.example.CommandInfo;
import java.util.NoSuchElementException;

@CommandInfo(name = "SQRT")
public class SqrtCommand implements Command {
    @Override
    public void execute(Context context, String[] args) throws Exception {
        double element;
        try {
            element = context.popFromStack();
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Not enough elements to perform sqrt");
        }
        if (element < 0) {
            context.pushOnStack(element);
            throw new RuntimeException("Square root of negative number " + element + " is not defined");
        }
        context.pushOnStack(Math.sqrt(element));
    }
}
