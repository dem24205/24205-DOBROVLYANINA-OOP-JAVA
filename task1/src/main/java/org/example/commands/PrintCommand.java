package org.example.commands;

import org.example.Command;
import org.example.Context;
import org.example.CommandInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Команда печати верхнего элемента стека.
 * Формат: PRINT (без аргументов)
 * Выводит значение через логгер (не удаляя из стека).
 */
@CommandInfo(name = "PRINT")
public class PrintCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(PrintCommand.class);

    @Override
    public void execute(Context context, String[] args) throws Exception {
        if (context.isStackEmpty()) {
            throw new RuntimeException("Nothing to print: stack is empty");
        }
        double value = context.peekStack();
        logger.info("{}", value);
    }
}
