package org.example.commands.binary;

import org.example.Context;
import org.example.CommandInfo;

@CommandInfo(name = "/")
public class DivCommand extends BinaryOperationCommand {
    @Override
    public void execute(Context context, String[] args) throws Exception {
        try {
            super.execute(context, args);
        } catch (Exception e) {
            throw new RuntimeException("Not enough elements to perform division");
        }
        if (elem2 == 0) {
            context.pushOnStack(elem2);
            context.pushOnStack(elem1);
            throw new RuntimeException("Division by zero");
        }
        context.pushOnStack(elem1 / elem2);
    }
}
