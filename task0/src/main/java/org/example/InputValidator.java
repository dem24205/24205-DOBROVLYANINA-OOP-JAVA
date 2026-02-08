package org.example;

import java.util.HashSet;
import java.util.Set;

public class InputValidator {
    private final int answerLength;

    public InputValidator(int length) {
        this.answerLength = length;
    }

    private boolean validatePermissibleSymbols(String input) {
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    private boolean validateUniqueDigits(String input) {
        Set<Character> seen = new HashSet<>();
        for (char c : input.toCharArray()) {
            if (seen.contains(c)) return false;
            seen.add(c);
        }
        return true;
    }

    public ValidationResult validate(String input) {
        if (input == null) {
            return ValidationResult.failure("Error: empty input");
        }
        if (input.length() != answerLength) {
            return ValidationResult.failure("Error: incorrect code length");

        }
        if (!validatePermissibleSymbols(input)) {
            return ValidationResult.failure("Error: only digits are allowed");
        }
        if(!validateUniqueDigits(input)) {
            return ValidationResult.failure("Error: only unique digits are allowed");
        }
        return ValidationResult.success(input);
    }

    public static class ValidationResult {
        private final boolean isValid;
        private final String errorMessage;

        private ValidationResult(boolean isValid, String errorMessage) {
            this.isValid = isValid;
            this.errorMessage = errorMessage;
        }

        public static ValidationResult success(String value) {
            return new ValidationResult(true, null);
        }

        public static ValidationResult failure(String errorMessage) {
            return new ValidationResult(false,  errorMessage);
        }

        public boolean isValid() { return isValid; }

        public String getErrorMessage() { return errorMessage; }
    }
}
