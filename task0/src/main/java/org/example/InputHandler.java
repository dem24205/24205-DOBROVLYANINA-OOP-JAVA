package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;
    private final InputValidator validator;
    private static final Logger log = LoggerFactory.getLogger(InputHandler.class);

    public InputHandler(int codeLength) {
        this.scanner = new Scanner(System.in);
        this.validator = new InputValidator(codeLength);
    }

    /**
     * Читает ввод игрока, пока не будет введен корректный код
     * @return корректная строка с кодом
     */
    public String readPlayerInput() {
        while (true) {
            log.info("Enter code: ");
            String input = scanner.nextLine().trim();
            InputValidator.ValidationResult result = validator.validate(input);
            if (result.isValid()) {
                log.debug("Player entered valid code: {}", input);
                return input;
            }
            log.warn("Invalid input: {} - {}", input, result.getErrorMessage());
        }
    }
}