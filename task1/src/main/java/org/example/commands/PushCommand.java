package org.example.commands;

import org.example.Command;
import org.example.Context;
import org.example.CommandInfo;
import java.util.NoSuchElementException;

@CommandInfo(name = "PUSH")
public class PushCommand implements Command {
    @Override
    public void execute(Context context, String[] args) throws Exception {
        double number;
        try {
            number = context.getVariable(args[0]);
            context.pushOnStack(number);
        } catch (NoSuchElementException e) {
            try {
                number = Double.parseDouble(args[0]);
                context.pushOnStack(number);
            }
            catch (NumberFormatException ne) {
                throw new RuntimeException("Variable not found, and argument is not a number: " + args[0]);
            }
        }
    }
}
