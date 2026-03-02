package org.example.commands.binary;

import org.example.Command;
import org.example.Context;

import java.util.NoSuchElementException;

public class BinaryOperationCommand implements Command {
    double elem1;
    double elem2;
    @Override
    public void execute(Context context, String[] args) throws Exception {
        try {
            elem2 = context.popFromStack();
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Not enough elements to perform the operation");
        }
        try {
            elem1 = context.popFromStack();
        } catch (NoSuchElementException e) {
            context.pushOnStack(elem1);
            throw new RuntimeException("Not enough elements to perform the operation");
        }
    }
}
