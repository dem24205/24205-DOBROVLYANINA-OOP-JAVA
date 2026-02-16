package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//-d=6 -a=3

public class GameConfig {
    private final int codeLength;
    private final int attemptsNum;

    private static final int DEFAULT_CODE_LENGTH = 4;
    private static final int DEFAULT_ATTEMPTS_NUM = 5;

    private static final int MAX_DIGITS = 6;
    private static final int MIN_DIGITS = 3;
    private static final int MIN_ATTEMPTS = 1;
    private static final int MAX_ATTEMPTS = 15;

    private GameConfig(int codeLength, int attemptsNum) {
        this.codeLength = codeLength;
        this.attemptsNum = attemptsNum;
    }

    public static GameConfig defaultConfig() {
        return new GameConfig(DEFAULT_CODE_LENGTH, DEFAULT_ATTEMPTS_NUM);
    }

    private static void validateArgs(int codeLength, int attemptsNum) {
        if (codeLength > MAX_DIGITS || codeLength < MIN_DIGITS) {
            throw new IllegalArgumentException("Code length must be between " + MIN_DIGITS + " and " + MAX_DIGITS);
        }
        if (attemptsNum < MIN_ATTEMPTS || attemptsNum > MAX_ATTEMPTS) {
            throw new IllegalArgumentException("Number of attempts must be between " + MIN_ATTEMPTS + " and " + MAX_ATTEMPTS);
        }
    }

    public static GameConfig fromArgs(String[] args) {
        if (args.length == 0) {
            return defaultConfig();
        }

        int codeLength = DEFAULT_CODE_LENGTH;
        int attemptsNum = DEFAULT_ATTEMPTS_NUM;
        Pattern codePattern = Pattern.compile("-d=(\\d+)");
        Pattern attemptsPattern = Pattern.compile("-a=(\\d+)");

        for (String arg : args) {
            Matcher codeMatcher = codePattern.matcher(arg);
            Matcher attemptsMatcher = attemptsPattern.matcher(arg);
            if (codeMatcher.matches()) {
                codeLength = Integer.parseInt(codeMatcher.group(1));
            } else if (attemptsMatcher.matches()) {
                attemptsNum = Integer.parseInt(attemptsMatcher.group(1));
            } else {
                throw new IllegalArgumentException("Invalid argument format: " + arg +
                        ". Expected -d=X or -a=X");
            }
        }

        validateArgs(codeLength, attemptsNum);
        return new GameConfig(codeLength, attemptsNum);
    }

    public int getCodeLength() {
        return this.codeLength;
    }

    public int getAttemptsNum() {
        return this.attemptsNum;
    }
}