package org.example;

public class Game {
    private final String answer;
    private final int attemptsNum;
    private boolean isGameWon;
    private final InputHandler inputHandler;
    private Guess guess;

    public Game(GameConfig config) {
        Generator gen = new Generator(config.getCodeLength());
        this.answer = gen.generate();
        this.attemptsNum = config.getAttemptsNum();
        this.isGameWon = false;
        this.inputHandler = new InputHandler(answer.length());
        this.guess = new Guess(0, 0);
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

    private void printAttempt(int attempt) {
        System.out.println("Attempt: " + attempt);
    }

    public void run() {
        printGreeting();
        for (int i = 0; i < attemptsNum; ++i) {
            int attempt = i + 1;
            printAttempt(attempt);
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
