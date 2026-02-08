package org.example;
import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;
    private final InputValidator validator;

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
            System.out.println(result.getErrorMessage());
        }
    }
}
