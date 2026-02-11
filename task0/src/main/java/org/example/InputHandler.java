package org.example;

import java.util.logging.*;
import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;
    private final InputValidator validator;
    private static final Logger logger = LoggerManager.getLogger(InputHandler.class.getName());
    public InputHandler(int codeLength) {
        this.scanner = new Scanner(System.in);
        this.validator = new InputValidator(codeLength);
    }

    public String readPlayerInput() {
        while (true) {
            System.out.print("Enter code: ");
            String input = scanner.nextLine().trim();
            InputValidator.ValidationResult result = validator.validate(input);
            if (result.isValid()) {
                return input;
            }
            logger.info("Invalid input attempt: " + input + " - " + result.getErrorMessage());
            System.out.println(result.getErrorMessage());
        }
    }
}
