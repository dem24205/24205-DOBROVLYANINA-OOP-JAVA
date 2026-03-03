package org.example.commands.binary;

import org.example.Context;
import org.example.CommandInfo;

/**
 * Команда вычитания.
 * Формат: - (без аргументов)
 * Вычитает первый (верхний) элемент стека из второго (elem1 - elem2).
 */
@CommandInfo(name = "-")
public class SubCommand extends BinaryOperationCommand {
    @Override
    public void execute(Context context, String[] args) throws Exception {
        try {
            super.execute(context, args);
        } catch (Exception e) {
            throw new RuntimeException("Not enough elements to perform substraction");
        }
        context.pushOnStack(elem1 - elem2);
    }
}
