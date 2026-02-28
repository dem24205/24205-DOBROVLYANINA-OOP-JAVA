package org.example.commands;

import org.example.Command;
import org.example.Context;
import org.example.CommandInfo;
import java.util.NoSuchElementException;

@CommandInfo(name = "POP")
public class PopCommand implements Command {
    @Override
    public void execute(Context context, String[] args) throws Exception {
        double element;
        try {
            element = context.popFromStack();
        } catch (NoSuchElementException e) {
            throw new RuntimeException(e.getMessage());
        }
        if (args.length == 0) {
            return;
        }
        if (!args[0].matches("^[A-Za-z][A-Za-z0-9_]*$")) {
            throw new RuntimeException("Invalid variable name: " + args[0]);
        }
        context.setVariable(args[0], element);
    }
}
