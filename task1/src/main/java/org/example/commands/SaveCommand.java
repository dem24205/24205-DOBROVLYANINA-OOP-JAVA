package org.example.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.CalculatorState;
import org.example.Command;
import org.example.Context;
import org.example.CommandInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Команда сохранения состояния калькулятора в JSON файл.
 * Format: SAVE filename.json
 * Сохраняет текущий стек, переменные и историю команд.
 */
@CommandInfo(name = "SAVE")
public class SaveCommand implements Command {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(SaveCommand.class);
    @Override
    public void execute(Context context, String[] args) throws Exception {
        if (args.length == 0) {
            throw new RuntimeException("Usage: SAVE <filename.json>");
        }
        if (!args[0].matches(".*\\.json$")) {
            throw new RuntimeException("Usage save <filename.json>");
        }
        CalculatorState state = new CalculatorState();
        state.setStack(context.getStack());
        state.setVariables(context.getVariables());
        state.setHistory(context.getHistory());
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(args[0]), state);
        logger.info("Saved context to {}", args[0]);
    }
}
