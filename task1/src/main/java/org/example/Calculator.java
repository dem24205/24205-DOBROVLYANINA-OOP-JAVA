package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Scanner;

/**
 * Основной класс калькулятора, управляющий выполнением команд.
 * Читает команды из переданного Scanner, парсит их и выполняет через фабрику команд.
 */
public class Calculator {
    private static final Logger logger = LoggerFactory.getLogger(Calculator.class);
    /**
     * Запускает цикл обработки команд калькулятора.
     * Последовательно читает строки из scanner.
     * Парсит их на имя команды и аргументы.
     * Создает команду через фабрику и выполняет её.
     * Пустые строки и комментарии игнорируются.
     * Команда EXIT завершает работу калькулятора (в случае интерактивного ввода).
     * При ошибках выполнения команд работа продолжается (ошибки логируются).
     *
     * @param scanner источник ввода команд (может быть System.in или файл)
     */
    public void run(Scanner scanner) {
        logger.info("Calculator has successfully run");
        Context context = new Context();
        InputParser parser = new InputParser();
        CommandFactory commandFactory;

        try {
            commandFactory = new CommandFactory();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return;
        }

        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            Command command;
            String commandName = parser.getCommandName(input);
            if (commandName == null) {
                continue;
            }
            if (commandName.equals("EXIT")) {
                logger.info("Exit command received");
                return;
            }
            try {
                command = commandFactory.createCommand(commandName);
                command.execute(context, parser.getAttributes(input));
                context.addToHistory(input);
            } catch (Exception e) {
                logger.warn(e.getMessage());
            }
        }
       logger.info("Calculator has successfully finished its work");
    }
}
