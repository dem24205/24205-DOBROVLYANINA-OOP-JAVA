package org.example.commands.binary;

import org.example.Context;
import org.example.CommandInfo;

/**
 * Команда сложения.
 * Формат: + (без аргументов)
 * Складывает два верхних элемента стека (elem1 + elem2).
 */
@CommandInfo(name = "+")
public class SumCommand extends BinaryOperationCommand{
    @Override
    public void execute(Context context, String[] args) throws Exception {
        try {
            super.execute(context, args);
        } catch (Exception e) {
            throw new RuntimeException("Not enough elements to perform summarization");
        }
        context.pushOnStack(elem1 + elem2);
    }
}
