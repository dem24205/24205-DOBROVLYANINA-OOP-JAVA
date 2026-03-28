package org.example;

import java.util.Arrays;

/**
 * Парсер входных строк калькулятора.
 * Разделяет строку на имя команды и аргументы.
 */
public class InputParser {
    /**
     * Извлекает имя команды из входной строки.
     * @param input входная строка
     * @return имя команды или null если строка пустая/комментарий
     */
    public String getCommandName(String input) {
        if (input == null || input.trim().isEmpty()) {
            return null;
        }
        String[] inputArray = input.trim().split("\\s+");
        if (inputArray[0].startsWith("#")) {
            return null;
        }
        return inputArray[0].trim();
    }

    /**
     * Извлекает аргументы команды из входной строки.
     * @param input входная строка
     * @return массив аргументов (без имени команды), пустой массив если аргументов нет
     */
    public String[] getAttributes(String input) {
        if (input == null || input.trim().isEmpty()) {
            return new String[0];
        }
        String[] inputArray = input.trim().split("\\s+");
        return Arrays.copyOfRange(inputArray, 1, inputArray.length);
    }
}

