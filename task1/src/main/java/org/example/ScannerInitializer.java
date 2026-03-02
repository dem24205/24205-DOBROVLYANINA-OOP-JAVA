package org.example;

import java.io.File;
import java.util.Scanner;

public class ScannerInitializer {
    private final InputMode mode;
    private final String fileName;

    public ScannerInitializer (String[] args) {
        if (args.length == 0 || !args[0].matches("[A-Za-z0-9]*.txt")) {
            mode = InputMode.CONSOLE_MODE;
            fileName = null;
        }
        else {
            mode = InputMode.FILE_MODE;
            fileName = args[0];
        }
    }

    public Scanner initScanner() throws Exception {
        if (mode == InputMode.CONSOLE_MODE) {
            return new Scanner(System.in);
        }
        return new Scanner(new File(fileName));
    }
}
