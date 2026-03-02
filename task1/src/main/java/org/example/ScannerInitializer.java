package org.example;

import java.io.File;
import java.util.Scanner;

/**
 * Инициализатор сканера для ввода команд.
 * Определяет режим работы на основе аргументов командной строки.
 */
public class ScannerInitializer {
    private final InputMode mode;
    private final String fileName;

    /**
     * Создаёт инициализатор на основе аргументов командной строки.
     * @param args аргументы командной строки
     */
    public ScannerInitializer (String[] args) {
        if (args.length == 0 || !args[0].matches("[A-Za-z0-9]*\\.txt")) {
            mode = InputMode.CONSOLE_MODE;
            fileName = null;
        } else {
            mode = InputMode.FILE_MODE;
            fileName = args[0];
        }
    }

    /**
     * Создаёт сканер в соответствии с выбранным режимом.
     * @return сканер для чтения команд
     * @throws Exception если файл не найден или недоступен
     */
    public Scanner initScanner() throws Exception {
        if (mode == InputMode.CONSOLE_MODE) {
            return new Scanner(System.in);
        }
        return new Scanner(new File(fileName));
    }
}