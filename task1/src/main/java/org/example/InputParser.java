package org.example;

import java.util.Arrays;

public class InputParser {
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

    public String[] getAttributes(String input) {
        if (input == null || input.trim().isEmpty()) {
            return new String[0];
        }
        String[] inputArray = input.trim().split("\\s+");
        return Arrays.copyOfRange(inputArray, 1, inputArray.length);
    }
}

