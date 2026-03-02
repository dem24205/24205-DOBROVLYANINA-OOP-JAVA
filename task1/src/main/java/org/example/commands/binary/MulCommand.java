package org.example.commands.binary;

import org.example.Context;
import org.example.CommandInfo;

/**
 * Команда умножения.
 * Формат: * (без аргументов)
 * Умножает второй элемент стека на первый (elem1 * elem2).
 */
@CommandInfo(name = "*")
public class MulCommand extends BinaryOperationCommand {
    @Override
    public void execute(Context context, String[] args) throws Exception{
        try {
            super.execute(context, args);
        } catch (Exception e) {
            throw new RuntimeException("Not enough elements to perform multiply");
        }
        context.pushOnStack(elem1 * elem2);
    }
}
