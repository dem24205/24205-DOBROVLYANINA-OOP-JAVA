package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Generator {
    private static final int TOTAL_DIGITS = 10;
    private final int digitsNumber;

    public Generator(int digitsNumber){
        this.digitsNumber = digitsNumber;
    }

    /**
     * Генерирует случайное число с неповторяющимися цифрами
     * @return строка из digitsNumber неповторяющихся цифр
     */
    public String generate() {
        List<Integer> digits = createDigitsList();
        Collections.shuffle(digits);
        return convertToString(digits);
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
}