package org.example;

public class GameConfig {
    private final int codeLength;
    private final int attemptsNum;

    private static final int DEFAULT_CODE_LENGTH = 4;
    private static final int DEFAULT_ATTEMPTS_NUM = 5;

    private static final int MAX_DIGITS = 10;
    private static final int MIN_DIGITS = 2;
    private static final int MIN_ATTEMPTS = 1;

    private GameConfig(int codeLength, int attemptsNum) {
        this.codeLength = codeLength;
        this.attemptsNum = attemptsNum;
    }

    public static GameConfig defaultConfig() {
        return new GameConfig(DEFAULT_CODE_LENGTH, DEFAULT_ATTEMPTS_NUM);
    }

    private static void validateArgs(int codeLength, int attemptsNum) {
        if (codeLength > MAX_DIGITS || codeLength < MIN_DIGITS) {
            throw new IllegalArgumentException("Incorrect code length");
        }
        if (attemptsNum < MIN_ATTEMPTS) {
            throw new IllegalArgumentException("Incorrect number of attempts");
        }
    }

    public static GameConfig fromArgs(String[] args) {
        if (args.length == 0) {
            return defaultConfig();
        }
        if (args.length != 2) {
            throw new IllegalArgumentException("Incorrect number of args");
        }

        try {
            int codeLength = Integer.parseInt(args[0]);
            int attemptsNum = Integer.parseInt(args[1]);
            validateArgs(codeLength, attemptsNum);
            return new GameConfig(codeLength, attemptsNum);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Only digits allowed as args");
        }
    }

    public int getCodeLength() {
        return this.codeLength;
    }

    public int getAttemptsNum() {
        return this.attemptsNum;
    }
}
