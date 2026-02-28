package org.example.commands;

import org.example.Command;
import org.example.Context;
import org.example.CommandInfo;

@CommandInfo(name = "DEFINE")
public class DefineCommand implements Command {
    @Override
    public void execute(Context context, String[] args) throws Exception {
        if (args.length < 2) {
            throw new RuntimeException("Usage: define <variable_name> <number>");
        }
        if (!args[0].matches("^[A-Za-z][A-Za-z0-9_]*$")) {
            throw new RuntimeException("Invalid variable name:"  + args[0]);
        }
        double number;
        try {
            number = Double.parseDouble(args[1]);
        }
        catch (NumberFormatException e) {
            throw new RuntimeException("Variable must be initialized with a number");
        }
        context.setVariable(args[0], number);
    }
}
