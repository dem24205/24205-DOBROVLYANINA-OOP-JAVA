package org.example;

import java.util.HashSet;
import java.util.Set;

public class InputValidator {
    private final int answerLength;
    public InputValidator(int length) {
        this.answerLength = length;
    }

    /**
     * Проверяет корректность введенного кода
     * @param input строка для проверки
     * @return ValidationResult с результатом проверки
     */
    public ValidationResult validate(String input) {
        if (input == null) {
            return ValidationResult.failure("empty input");
        }
        if (input.length() != answerLength) {
            return ValidationResult.failure("incorrect code length");

        }
        if (!validatePermissibleSymbols(input)) {
            return ValidationResult.failure( "only digits are allowed");
        }
        if(!validateUniqueDigits(input)) {
            return ValidationResult.failure("only unique digits are allowed");
        }
        return ValidationResult.success(input);
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

    /**
     * Результат валидации: успех/ошибка с сообщением
     */
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