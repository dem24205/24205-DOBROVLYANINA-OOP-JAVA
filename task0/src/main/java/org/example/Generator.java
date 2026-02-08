package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Generator {
    private static final int MAX_DIGITS = 10;
    private static final int MIN_DIGITS = 1;
    private static final int TOTAL_DIGITS = 10;

    private final int digitsNumber;

    private void validateDigitsNumber(int digitsNumber) {
        if (digitsNumber > MAX_DIGITS ||
        digitsNumber < MIN_DIGITS) {
            throw new IllegalArgumentException("Incorrect length of secret code");
        }
    }

    public Generator(int digitsNumber){
        validateDigitsNumber(digitsNumber);
        this.digitsNumber = digitsNumber;
    }

    private List<Integer> createDigitsList() {
        List<Integer> digits = new ArrayList<>(TOTAL_DIGITS);
        for (int i = 0; i < TOTAL_DIGITS; ++i) {
            digits.add(i);
        }
        return digits;
    }

    private String convertToString(List<Integer> digits) {
        StringBuilder sb = new StringBuilder(digitsNumber);
        for (int i = 0; i < digitsNumber; ++i) {
            sb.append(digits.get(i));
        }
        return sb.toString();
    }

    public String generate() {
        List<Integer> digits = createDigitsList();
        Collections.shuffle(digits);
        return convertToString(digits);
    }
}
