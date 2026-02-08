package org.example;

public class Game {
    private static final int MIN_ATTEMPTS = 1;
    private final String answer;
    private final int attemptsNum;
    private boolean isGameWon;
    private final InputHandler inputHandler;
    private Guess guess;

    public Game(int attemptsNum, int digitsNum) {
        if (attemptsNum < MIN_ATTEMPTS) {
            throw new IllegalArgumentException("Incorrect number of attempts");
        }
        Generator gen = new Generator(digitsNum);
        answer = gen.generate();
        this.attemptsNum = attemptsNum;
        this.isGameWon = false;
        inputHandler = new InputHandler(answer.length());
        guess = new Guess(0, 0);
    }

    private void printGreeting() {
        System.out.println("Welcome to Cows and Bulls game!");
        System.out.println("Try to guess " + answer.length() + "-digit code.");
        System.out.println("You have " + attemptsNum + " attempts.");
        System.out.println("Good luck!");
    }

    private void printHint() {
        System.out.println("Number of bulls: " + this.guess.getBulls() + "\n" +
                "Number of cows: " + this.guess.getCows() + "\n");
    }

    private void printWinMessage() {
        System.out.println("Congratulations!");
        System.out.println("You guessed the code: " + answer);
    }

    private void printLoseMessage() {
        System.out.println("Game over!");
        System.out.println("You've used all attempts!");
        System.out.println("The secret code was: " + answer);
    }

    public void run() {
        printGreeting();
        for (int i = 0; i < attemptsNum; ++i) {
            String inputCode = inputHandler.readPlayerInput();
            if (answer.equals(inputCode)) {
                isGameWon = true;
                printWinMessage();
                return;
            }
            else {
                this.guess = GameEngine.calculateBullsAndCows(answer, inputCode);
                printHint();
            }
        }
        if (!isGameWon) printLoseMessage();
    }
}
